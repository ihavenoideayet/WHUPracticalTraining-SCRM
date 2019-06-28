

import java.util.concurrent.TimeUnit;

public class Scanner{
    private boolean quit;
    private Thread thread;
    private ScanBarcodeService scanBarcodeService;
    public static boolean flag = false;
    public Scanner(){
        scanBarcodeService=new ScanBarcodeService();
    }
   
    public void startScan() {
//闃叉閲嶅鍚姩
        if(thread!=null && thread.isAlive()){
            return;
        }
        System.out.println("");
//鍚姩涓�涓嚎绋嬬敤浜庡湪鍏抽棴鐨勬椂鍊欏嵏杞介敭鐩橀挬瀛�
        thread=new Thread() {
            @Override
            public void run() {
                System.out.println("鏉＄爜鏋壂鎻忕嚎绋嬪惎鍔�");
                while (!quit) {
                    try {
                        Thread.sleep(Long.MAX_VALUE);
                    } catch (Exception e) {
                        quit=true;
                    }
                }
                scanBarcodeService.stopScanBarcodeService();
                System.out.println("鏉＄爜鏋壂鎻忕嚎绋嬮��鍑�");
                System.exit(0);
            }
        };
        thread.start();
        new Thread() {
            @Override
            public void run() {
                scanBarcodeService.startScanBarcodeService();
            }
        }.start();
    }
    
    
    public void stopScan(){
        if(thread!=null){
            thread.interrupt();
            System.out.println("鍋滄鏉″舰鐮佺敓浜ц��...");
        }
    }
        
	public static void main(String[]args)
	{
		Scanner Scanner = new Scanner();
		Scanner.startScan();
		
		try {
			TimeUnit.HOURS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
}
