import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

///////////////////////////////////////
// Main class
//
class Editor {
  public static void main(String argv[]) {
    // モデルの生成．
    EditorModel m = new EditorModel();

    // 一つ目のビュー
    EditorView v1 = new EditorView(m,"View 1");
    // １つ目のビューに対応するコントローラ
    EditorController c1 = new EditorController(m,v1);

    // ２つ目のビュー．ＭＶＣだとマルチビューも容易です．
    // フォントを少し変更してみます．
    Font font = new Font("SansSerif",Font.BOLD|Font.ITALIC,20);
    EditorView v2 = new EditorView(m,font,Color.red,"View 2");
    // ２つ目のニューに対応するコントローラ．
    // ビューとコントローラは密接に関係しているので，ビュー毎に
    // コントローラも生成します．
    EditorController c2 = new EditorController(m,v2);

    v1.setBounds(100,100,400,100);
    v1.setVisible(true);

    v2.setBounds(100,300,400,100);
    v2.setVisible(true);
  }
}

/////////////////////////////////////////////////////
// Model (M)

class EditorModel extends Observable {
  protected StringBuffer buffer = new StringBuffer();
  // 本来はカーソル位置はviewに持たせた方がいいが，modelに持たせるほうが
  // プログラムが簡単になるので，modelに持たせることにする．
  protected int cursorPosition = 0;

  public void insertCharAt(int pos,char c) {
    buffer.insert(pos,c);
    this.moveCursor(1);
    setChanged();
    notifyObservers();
  }
  public void deleteCharAt(int pos) {
    buffer.deleteCharAt(pos);
    this.moveCursor(-1);
    setChanged();
    notifyObservers();
  }
  public String getString(int start,int end) {
    return buffer.substring(start,end);
  }
  public String getString() {
    return new String(buffer);
  }
  public String getPrefix(int end) {
    if (end < 0) end = 0;
    if (end > buffer.length()) end = buffer.length();
    return buffer.substring(0,end);
  }
  public String getPostfix(int start) {
    if (start < 0) start = 0;
    if (start > buffer.length()) start = buffer.length();
    return buffer.substring(start);
  }
  public int getLength() {
    return buffer.length();
  }
  public String toString() {
    return new String(buffer);
  }
  public void moveCursor(int n) {
    int newPos = cursorPosition + n;
    if (0 <= newPos && newPos <= getLength()) {
      cursorPosition = newPos;
    }
    setChanged();
    notifyObservers();
  }
  public void moveCursorToLineTop() {
    cursorPosition = 0;
    setChanged();
    notifyObservers();
  }
  public void moveCursorToLineEnd() {
    cursorPosition = this.getLength();
    setChanged();
    notifyObservers();
  }
  public int getCursorPosition() {
    return cursorPosition;
  }
}

////////////////////////////////////////////////////
// View (V)

class EditorView extends JFrame implements Observer {
  protected EditorModel model;
  protected Font font;
  protected FontMetrics fontMetrics;
  protected Color color;
  protected ViewPanel panel;

  public EditorView(EditorModel m,String st) {
    this(m,new Font("Serif",Font.PLAIN,24),Color.black,st);
  }
  public EditorView(EditorModel m,Font f,Color c,String st) {
    super(st);
    model = m;
    model.addObserver(this);
    panel=new ViewPanel();
    this.add(panel);
    font = f;
    fontMetrics = panel.getFontMetrics(font);
    color = c;
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
  }

  // ViewPanel を EditorView の内部クラスとして実装
  class ViewPanel extends JPanel {
    ViewPanel(){ 
       this.setFocusable(true); // 文字入力可能とするためにキーボードフォーカスをセット
    }
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      drawString(g);  // 文字列の描画
      drawCursor(g);  // カーソルの描画
    }
    private void drawString(Graphics g) {
      String s = model.getString();
      int h = fontMetrics.getAscent();
      g.setColor(color);
      g.setFont(font);
      g.drawString(s,0,h);
    }
    private void drawCursor(Graphics g) {
      String pre  = model.getPrefix(model.getCursorPosition());
      String pre1 = model.getPrefix(model.getCursorPosition()+1);
      int h = fontMetrics.getAscent();
      // font classのstringWidthメソッドは，文字列の横の画素数を返します．
      int w0 = fontMetrics.stringWidth(pre);
      int w1 = fontMetrics.stringWidth(pre1);
      // currentPosition と currentPosition+1 までの横幅の差がカーソルの横幅
      int w = w1 - w0;
      if (w <= 0) w = 5;
      g.setColor(color);
      g.setXORMode(Color.white); // 白で反転させます．
      g.fillRect(w0,0,w,h);
    }
  }
  public void update(Observable o, Object arg) {
    panel.repaint();
  }
  public JPanel getPanel() { return panel; }
}

/////////////////////////////////////////////////////
// Controller (C)

// KeyListener が，キー操作のリスナーインタフェース．
class EditorController implements KeyListener {
  protected EditorModel model;
  protected EditorView view;
  public EditorController(EditorModel m,EditorView v) {
    model = m;
    view = v;
    v.getPanel().addKeyListener(this);
  }
  public void keyTyped(KeyEvent e) {
    // 通常のキーのイベントはkeyTypedで取得します．
    char c = e.getKeyChar();
    if (Character.isISOControl(c)) {
      switch (c) {
      case '\u0002': // ctrl-B
	model.moveCursor(-1);
	break;
      case '\u0006': // ctrl-F
	model.moveCursor(1);
	break;
      case '\u0001': // ctrl-A
	model.moveCursorToLineTop();
	break;
      case '\u0005': // ctrl-E
	model.moveCursorToLineEnd();
	break;
      case '\u0004': // ctrl-D
	model.deleteCharAt(model.getCursorPosition());
	break;
      case '\u0008': // ctrl-H (BackSpace)
	model.deleteCharAt(model.getCursorPosition() - 1);
	break;
      }
    } else {
      model.insertCharAt(model.getCursorPosition(),c);
    }
  }
  public void keyPressed(KeyEvent e){
    // カーソルキーのイベントはkeyPressedで取得します．
    int k = e.getKeyCode();
    switch (k) {
      case KeyEvent.VK_LEFT: // 左カーソル
	model.moveCursor(-1);
        break;
      case KeyEvent.VK_RIGHT: // 右カーソル
	model.moveCursor(1);
        break;
    }
  }
  public void keyReleased(KeyEvent e){}
}
    
