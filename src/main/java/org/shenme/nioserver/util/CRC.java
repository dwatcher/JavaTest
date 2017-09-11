package org.shenme.nioserver.util;

public class CRC {
    /**
     * CRC-CCITT(Kermit)验证模式
     * @param str 
     * @return
     */
	public String CRC_CCITT_Kermit(String str) {
		int j, b, rrrc, c, i;
		String tmpBalance;
		int k;
		rrrc = 0;
		tmpBalance = str;
		int tmpInt, CharInt;
		String tmpChar, tmpStr;
		tmpStr = "";
		int High;
		int Low;

	for (j = 1; j <= 3; j++) {

			if (Character.isDigit(tmpBalance.charAt(2 * j - 2))) {
				High = Integer.parseInt(tmpBalance.charAt(2 * j - 2) + "");

			} else {
				High = 0;

			}
			if (Character.isDigit(tmpBalance.charAt(2 * j - 1))) {
				Low = Integer.parseInt(tmpBalance.charAt(2 * j - 1) + "");

			} else {

				Low = 0;
			}

			High = (High & 0xff) << 4;

			High = High | Low;

			k = High;

			for (i = 1; i <= 8; i++) {
				c = rrrc & 1;
				rrrc = rrrc >> 1;
				if ((k & 1) != 0) {
					rrrc = rrrc | 0x8000;

				}
				if (c != 0) {

					rrrc = rrrc ^ 0x8408;
				}

				k = k >> 1;
			}
		}
		for (i = 1; i <= 16; i++) {
			c = rrrc & 1;
			rrrc = rrrc >> 1;

			if (c != 0) {

				rrrc = rrrc ^ 0x8408;
			}

		}
		c = rrrc >> 8;
		b = rrrc << 8;
		rrrc = c | b;
		tmpInt = rrrc;
		tmpStr = "";
		for (i = 1; i <= 4; i++) {
			tmpChar = "";
			CharInt = tmpInt % 16;
			if (CharInt > 9) {
				switch (CharInt) {
				case 10:
					tmpChar = "A";
					break;
				case 11:

					tmpChar = "B";
					break;
				case 12:
					tmpChar = "C";
					break;

				case 13:

					tmpChar = "D";
					break;

				case 14:

					tmpChar = "E";
					break;

				case 15:

					tmpChar = "F";
					break;

				}
			} else {

				tmpChar = Integer.toString(CharInt);
			}

			tmpInt = tmpInt / 16;
			tmpStr = tmpChar + tmpStr;

		}

		System.out.println("tmpStr:" + tmpStr);

		return tmpStr;

	}
	
	/**
	 * CRC-CCITT(XModem)
	 * CRC-CCITT(0xFFFF)  
	 * CRC-CCITT(0x1D0F) 
	 * 校验模式
	 * @param flag< XModem(flag=1) 0xFFFF(flag=2) 0x1D0F(flag=3)>
	 * @param str
	 * @return
	 */
	public String  CRC_CCITT( int flag,String str) { 
        int crc = 0x00;          // initial value
        int polynomial = 0x1021;   
        byte[] bytes=str.getBytes();
        
        switch(flag){
        case 1:
        	crc=0x00;
        	break;
        case 2:
        	crc=0xFFFF;
        	break;
        case 3:
        	crc=0x1D0F;
        	break;
        
        }
        for (int index = 0 ; index< bytes.length; index++) {
            byte b = bytes[index];
            for (int i = 0; i < 8; i++) {
                boolean bit = ((b   >> (7-i) & 1) == 1);
                boolean c15 = ((crc >> 15    & 1) == 1);
                crc <<= 1;
                if (c15 ^ bit) crc ^= polynomial;
             }
        }
        crc &= 0xffff;
        str = Integer.toHexString(crc);
        System.out.println(str);
        return str;
        
    }
	
	public static short crc16(byte[] data) {
        short crc = (short) 0xFFFF;
        short dxs = (short) 0xA001;
        byte tc;
        byte sbit;
        for (int i = 0; i < data.length; i++) {
            tc = (byte) ((crc & 0xff00) >> 8);
            crc = (short) (tc ^ data[i]);
            for (int r = 0; r < 8; r++) {
                sbit = (byte) (crc & 0x01);
                crc = (short) (crc >> 1);
                if (sbit != 0)
                    crc = (short) (crc ^ dxs);
            }
        }

        System.out.println(bytesToHexString(new byte[] {
                (byte) ((crc & 0xff00) >> 8), (byte) (crc & 0xff) }));

        return crc;
    }

    //将字节数组按16进制输出
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);

            if (stringBuilder.length() != 0) {
                stringBuilder.append(",");
            }
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }
    
    public static String getCrc(byte[] data,int strLength) {  
        int high;  
        int flag;  
  
        // 16位寄存器，所有数位均�?  
        int wcrc = 0xffff;  
        for (int i = 0; i < data.length; i++) {  
            // 16 位寄存器的高位字�? 
            high = wcrc >> 8;  
            // 取被校验串的�?��字节�?16 位寄存器的高位字节进行�?异或”运�? 
            wcrc = high ^ data[i];  
  
            for (int j = 0; j < 8; j++) {  
                flag = wcrc & 0x0001;  
                // 把这�?16 寄存器向右移�?��  
                wcrc = wcrc >> 1;  
                // 若向�?标记�?移出的数位是 1,则生成多项式 1010 0000 0000 0001 和这个寄存器进行“异或�?运算  
                if (flag == 1)  
                    wcrc ^= 0xa001;  
            }  
        }  
  
        String st = Integer.toHexString(wcrc);
        return addZeroForNum(st,strLength).toUpperCase();  
    } 
    
    public static String addZeroForNum(String str, int strLength) {
        int strLen = str.length();
        StringBuffer sb = null;
        while (strLen < strLength) {
              sb = new StringBuffer();
              sb.append("0").append(str);// �?�?�?
           // sb.append(str).append("0");//�?�?�?
              str = sb.toString();
              strLen = str.length();
        }
        return str;
    }

	
}
	