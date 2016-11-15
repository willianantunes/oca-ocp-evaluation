package br.com.willianantunes.exam;

public class Mock3ExamHurtException {
    private void split() throws HurtException{throw new HurtException();}
     
    public void run(){
        try{
            split();
        }
        catch (HurtException e){System.out.println("HurtException");}
        catch (LimpException e) {System.out.println("LimpException");}
        catch (Exception e){}
    }
     
    public static void main(String[] args) {
        Mock3ExamHurtException m = new Mock3ExamHurtException();
        m.run();
    }
}
 
class LimpException extends Exception{}
class HurtException extends LimpException{}