import javax.swing.JButton;

class MyJButton extends JButton
{
  private int num;
  
  public MyJButton ( String text )
  {
    super (text);
    //setText (text);
  }
  
  
  public MyJButton ( String text , int n)
  {
    super (text);
    num = n;
  }
  
  public void setNumber (int n)
  {
    num = n;
  }
  
  public int getNumber ()
  {
    return num;
  }
}
