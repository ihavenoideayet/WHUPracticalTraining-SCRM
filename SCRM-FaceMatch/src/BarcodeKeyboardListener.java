

import java.util.HashMap;
import java.util.Map;

/**
 * 鎵爜鏋ā鎷熺殑閿洏鎸夐挳浜嬩欢鐩戝惉锛�0-9閿拰鍥炶溅閿級 鍏抽敭绠楁硶锛氭潯褰㈢爜鎵弿鍣ㄥ湪寰堢煭鐨勬椂闂村唴杈撳叆浜嗚嚦灏� barcodeMinLength
 * 涓瓧绗︿互涓婁俊鎭紝骞朵笖浠モ�滃洖杞︹�濅綔涓虹粨鏉熷瓧绗︼紝骞朵笖涓�娆℃壂鎻忚鍦� maxScanTime 姣鍐呭畬鎴� 瀛楃鏁板強鎵弿鏃堕棿鍙牴鎹叿浣撴儏鍐佃缃�
 */
public class BarcodeKeyboardListener {
	// 鏉″舰鐮佹暟鎹紦鍏呭尯
	private StringBuilder barcode;
	// 鎵弿寮�濮嬫椂闂�
	private long start;
	private Map<Integer, Integer> keyToLetter = new HashMap();
	// 涓�娆℃壂鎻忕殑鏈�闀挎椂闂�
	private static int maxScanTime = 1500;
	// 鏉″舰鐮佺殑鏈�鐭暱搴�
	private static int barcodeMinLength = 13;

	/**
	 * 鍒濆閿洏浠ｇ爜鍜屽瓧姣嶇殑瀵逛簬鍏崇郴
	 */
	public BarcodeKeyboardListener() {
		keyToLetter.put(48, 0);
		keyToLetter.put(49, 1);
		keyToLetter.put(50, 2);
		keyToLetter.put(51, 3);
		keyToLetter.put(52, 4);
		keyToLetter.put(53, 5);
		keyToLetter.put(54, 6);
		keyToLetter.put(55, 7);
		keyToLetter.put(56, 8);
		keyToLetter.put(57, 9);
	}

	/**
	 * 姝ゆ柟娉曞搷搴旀壂鎻忔灙浜嬩欢
	 * 
	 * @param keyCode
	 */
	public void onKey(int keyCode) {
//鑾峰彇杈撳叆鐨勬槸閭ｄ釜鏁板瓧
		Integer letter = keyToLetter.get(keyCode);
		if (barcode == null) {
			// 寮�濮嬭繘鍏ユ壂鎻忕姸鎬�
			barcode = new StringBuilder();
			// 璁板綍寮�濮嬫壂鎻忔椂闂�
			start = System.currentTimeMillis();
		}
		// 闇�瑕佸垽鏂椂闂�
		long cost = System.currentTimeMillis() - start;
		if (cost > maxScanTime) {
			// 寮�濮嬭繘鍏ユ壂鎻忕姸鎬�
			barcode = new StringBuilder();
			// 璁板綍寮�濮嬫壂鎻忔椂闂�
			start = System.currentTimeMillis();
		}

		// 鏁板瓧閿�0-9
		if (keyCode >= 48 && keyCode <= 57) {
			barcode.append(letter);
		}
		// 鍥炶溅閿�
		if (keyCode == 13) {
//鏉″舰鐮佹壂鎻忓櫒鍦ㄥ緢鐭殑鏃堕棿鍐呰緭鍏ヤ簡鑷冲皯 barcodeMinLength 涓瓧绗︿互涓婁俊鎭紝骞朵笖浠モ�滃洖杞︹�濅綔涓虹粨鏉熷瓧绗�
//杩涘叆杩欎釜鏂规硶琛ㄧず鏄�滃洖杞︹��
//閭ｄ箞鍒ゆ柇鍥炶溅涔嬪墠杈撳叆鐨勫瓧绗︽暟锛岃嚦灏� barcodeMinLength 涓瓧绗�
//骞朵笖涓�娆℃壂鎻忚鍦� maxScanTime 姣鍐呭畬鎴�
			if (barcode.length() >= barcodeMinLength && cost < maxScanTime) {
				cost = System.currentTimeMillis() - start;
				System.out.println("鑰楁椂锛�" + cost);
				System.out.println(barcode.toString());
				// 鏁版嵁澶勭悊		
				CommodityBuffer.addCommodity(Long.parseLong(barcode.toString()));
				}
//娓呯┖鍘熸潵鐨勭紦鍐插尯
			barcode = new StringBuilder();
		}
	}

}