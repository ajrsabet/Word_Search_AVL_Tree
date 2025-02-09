package util;

public class TypeWriter {
   private int delay = 5;   
   
   public TypeWriter() {
      }
   
      public void SlowType(String line) {
        for (int i = 0; i < line.length(); i++) {
           System.out.print(line.charAt(i));
           try {
              Thread.sleep(delay);
           } catch (InterruptedException e) {
              throw new RuntimeException(e);
           }
        }
        System.out.println();

   }
}
