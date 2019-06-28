
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.platform.win32.WinDef.HMODULE;
import com.sun.jna.platform.win32.WinDef.LPARAM;
import com.sun.jna.platform.win32.WinDef.LRESULT;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.platform.win32.WinUser.HHOOK;
import com.sun.jna.platform.win32.WinUser.KBDLLHOOKSTRUCT;
import com.sun.jna.platform.win32.WinUser.LowLevelKeyboardProc;
import com.sun.jna.platform.win32.WinUser.MSG;
/**
 *
 * 鍒╃敤HOOK鎶�鏈洃鍚敭鐩樹簨浠讹紙鎵弿鏋氨鐩稿綋浜庡彧鏈�0-9鍜屽洖杞﹂敭鐨勯敭鐩橈級
 * 浣庣骇閿洏浜嬩欢鐩戝惉
 *
 */
public class ScanBarcodeService {
	private HHOOK hhk;
	private final User32 lib = User32.INSTANCE;
	
	//鍋滄鎵爜鏋湇鍔�
	public void stopScanBarcodeService() {
		//鍗歌浇閿洏閽╁瓙
		lib.UnhookWindowsHookEx(hhk);
	}
	/**
	 * 鍚姩鎵爜鏋湇鍔�
	 */
	public void startScanBarcodeService() {
//閿洏浜嬩欢鐩戝惉
		final BarcodeKeyboardListener listener=new BarcodeKeyboardListener();
//鍥炶皟
		LowLevelKeyboardProc keyboardHook = new LowLevelKeyboardProc() {
			@Override
			public LRESULT callback(int nCode, WPARAM wParam,KBDLLHOOKSTRUCT info) {
				if (nCode >= 0) {
					switch (wParam.intValue()) {					
						case WinUser.WM_KEYUP:
							int keyCode = info.vkCode;
                            //鐩戝惉鏁板瓧閿�0-9
							if (keyCode >= 48 && keyCode <= 57) {                               
								listener.onKey(keyCode); //浜ょ粰鐩戝惉鍣ㄥ鐞�
							}
                             //鐩戝惉鍥炶溅閿�
							if (keyCode == 13) {								
								listener.onKey(keyCode);//浜ょ粰鐩戝惉鍣ㄥ鐞�
							}
							break;
					}
				}
                //浜や釜涓嬩竴涓挬瀛�
                Pointer ptr = info.getPointer();
                long peer = Pointer.nativeValue(ptr);
				return lib.CallNextHookEx(hhk, nCode, wParam,new LPARAM(peer));
			}
		};		
        //灏嗕笂闈㈠畾涔夌殑 鍥炶皟鏂规硶 瀹夎鍒版寕閽╅摼涓绯荤粺鐨勫簳灞傜殑閿洏杈撳叆浜嬩欢杩涜鐩戞帶
		HMODULE hMod = Kernel32.INSTANCE.GetModuleHandle(null);
		hhk = lib.SetWindowsHookEx(WinUser.WH_KEYBOARD_LL, keyboardHook, hMod, 0);
		
        // 澶勭悊娑堟伅锛堢嚎绋嬮樆濉烇級
		int result;
		MSG msg = new MSG();
		while ((result = lib.GetMessage(msg, null, 0, 0)) != 0) {
			if (result == -1) {
				System.err.println("error in get message");
				break;
			} else {
				lib.TranslateMessage(msg);
				lib.DispatchMessage(msg);
			}
		}
	}
}