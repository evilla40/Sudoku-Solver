import javax.swing.JButton;

class MyJButton extends JButton
{
  private int num;
  private boolean locked;
  
  public MyJButton ( String text )
  {
    super (text);
    num = 0;
    locked = false;
  }
  
  
  public MyJButton ( String text , int n, boolean b)
  {
    super (text);
    num = n;
    locked = b;
  }
  
  public void setNumber (int n)
  {
    num = n;
  }
  
  public int getNumber ()
  {
    return num;
  }
  
  public void setLock(boolean b) {
	  locked = b;
  }
  
  public boolean getLock() {
	  return locked;
  }
}
