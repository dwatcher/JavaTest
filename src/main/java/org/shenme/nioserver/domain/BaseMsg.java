/**  
* 文件�? BaseMsg.java
* 包名:	com.yunzhichina.nioserver.domain
* 功能: 	TODO
* 作�?:  	Fredric  
* @date 2017-3-29
*/
package org.shenme.nioserver.domain;

/**
 * 类名: BaseMsg
 * 描述: TODO
 * 作�?: Fredric
 * 日期: 2017-3-29
 */
public class BaseMsg {
	
	public long QN; 
	
	public Integer ST; 
	
	public Integer CN; 
	
	public String PW; 
	
	public String MN; 
	
	public Integer Flag; 
	
	public String CP; 
	
	public String CRC; 
	
	public String Logon; 
	
	

	public long getQN() {
		return QN;
	}



	public void setQN(long qN) {
		QN = qN;
	}



	public Integer getST() {
		return ST;
	}



	public void setST(Integer sT) {
		ST = sT;
	}



	public Integer getCN() {
		return CN;
	}



	public void setCN(Integer cN) {
		CN = cN;
	}



	public String getPW() {
		return PW;
	}



	public void setPW(String pW) {
		PW = pW;
	}



	public String getMN() {
		return MN;
	}



	public void setMN(String mN) {
		MN = mN;
	}



	public Integer getFlag() {
		return Flag;
	}



	public void setFlag(Integer flag) {
		Flag = flag;
	}



	public String getCP() {
		return CP;
	}



	public void setCP(String cP) {
		CP = cP;
	}



	public String getCRC() {
		return CRC;
	}



	public void setCRC(String cRC) {
		CRC = cRC;
	}



	public String getLogon() {
		return Logon;
	}



	public void setLogon(String logon) {
		Logon = logon;
	}



	@Override
	public String toString() {
		return "BaseMsg [QN=" + QN + ", ST=" + ST
				+ ", CN=" + CN + ",PW=" + PW + ",MN=" + MN + ",Flag=" + Flag + ",CP=" + CP + ",CRC=" + CRC + ",Logon=" + Logon + "]";
	}
	
	
}
