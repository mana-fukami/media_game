import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

// カーソルがview毎に独立したバージョン
// view がカーソル位置を保持しています。

///////////////////////////////////////
// Main class
//
class Editor2 {
  public static void main(String argv[]) {
    // モデルの生成．
    EditorModel m = new EditorModel();

    // 一つ目のビュー
    EditorView v1 = new EditorView(m,"View 1");
    // １つ目のビューに対応するコントローラ
    EditorController c1 = new EditorController(m,v1);

    // ２つ目のビュー．ＭＶＣだとマルチビューも容易です．
    // フォントを少し変更してみます．
    Font font = new Font("SansSerif",Font.BOLD|Font.ITALIC,28);
    EditorView v2 = new EditorView(m,font,Color.blue,"View 2");
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
  public void insertCharAt(int pos,char c) {
    try {
      buffer.insert(pos,c);
      notify(new InsertEvent(pos));
    } catch (StringIndexOutOfBoundsException e) {
    }
  }
  public void deleteCharAt(int pos) {
    try {
      buffer.deleteCharAt(pos);
      notify(new DeleteEvent(pos));
    } catch (StringIndexOutOfBoundsException e) {
    }
  }
  public String getString(int start,int end) {
    try {
      return buffer.substring(start,end);
    } catch (StringIndexOutOfBoundsException e) {
      return "";
    }
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
  private void notify(EditorModelEvent e) {
    setChanged();
    notifyObservers(e);
  }
}

class EditorModelEvent {
  private int position;
  public EditorModelEvent(int pos) {
    position = pos;
  }
  public int getPosition() {
    return position;
  }
}

class InsertEvent extends EditorModelEvent {
  public InsertEvent(int pos) {
    super(pos);
  }
}

class DeleteEvent extends EditorModelEvent {
  public DeleteEvent(int pos) {
    super(pos);
  }
}

////////////////////////////////////////////////////
// View (V)

class EditorView extends JFrame implements Observer {
  protected int cursorPosition = 0;
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
      String pre = model.getPrefix(cursorPosition);
      String pre1 = model.getPrefix(cursorPosition + 1);
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
    if (arg instanceof DeleteEvent) {
      DeleteEvent d = (DeleteEvent)arg;
      int pos = d.getPosition();
      if (pos < cursorPosition) {
	cursorPosition--;
      }
    }
    if (arg instanceof InsertEvent) {
      InsertEvent d = (InsertEvent)arg;
      int pos = d.getPosition();
      if (pos >= cursorPosition) {
	cursorPosition++;
      }
    }
    repaint();
  }
  public void moveCursor(int n) {
    int newPos = cursorPosition + n;
    if (0 <= newPos && newPos <= model.getLength()) {
      cursorPosition = newPos;
    }
    repaint();
  }
  public void moveCursorToLineTop() {
    cursorPosition = 0;
    repaint();
  }
  public void moveCursorToLineEnd() {
    cursorPosition = model.getLength();
    repaint();
  }
  public int getCursorPosition() {
    return cursorPosition;
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
    char c = e.getKeyChar();
    if (Character.isISOControl(c)) {
      switch (c) {
      case '\u0002': // ctrl-B
	view.moveCursor(-1);
	break;
      case '\u0006': // ctrl-F
	view.moveCursor(1);
	break;
      case '\u0001': // ctrl-A
	view.moveCursorToLineTop();
	break;
      case '\u0005': // ctrl-E
	view.moveCursorToLineEnd();
	break;
      case '\u0004': // ctrl-D
	model.deleteCharAt(view.getCursorPosition());
	break;
      case '\u0008': // ctrl-H (BackSpace)
	model.deleteCharAt(view.getCursorPosition() - 1);
	break;
      }
    } else {
      model.insertCharAt(view.getCursorPosition(),c);
    }
  }
  public void keyPressed(KeyEvent e){
      // カーソルキーのイベントはkeyPressedで取得します．
    int k = e.getKeyCode();
    switch (k) {
      case KeyEvent.VK_LEFT: // 左カーソル
	view.moveCursor(-1);
        break;
      case KeyEvent.VK_RIGHT: // 右カーソル
	view.moveCursor(1);
        break;
    }
  }
  public void keyReleased(KeyEvent e){}
}
    
