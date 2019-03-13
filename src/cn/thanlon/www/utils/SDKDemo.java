package cn.thanlon.www.utils;

import com.montnets.mwgate.common.GlobalParams;
import com.montnets.mwgate.common.Message;
import com.montnets.mwgate.smsutil.ConfigManager;
import com.montnets.mwgate.smsutil.SmsSendConn;

/**
 * @���ܸ�Ҫ��SDK����ʾ�� @��˾���ƣ� ShenZhen Montnets Technology CO.,LTD.
 */
public class SDKDemo {
	/**
	 * 
	 * @description SDKʾ��
	 * @param args
	 */
	public static void sendCodeWithTel(String tel, String code) {
		// �û��˺�
		String userid = "E10475";

		// ����ȫ�ֲ���
		GlobalParams globalParams = new GlobalParams();
		// ��������·��
		globalParams.setRequestPath("/sms/v2/std/");
		// �����Ƿ���Ҫ��־ 1:��Ҫ��־;0:����Ҫ��־
		globalParams.setNeedLog(1);
		// ����ȫ�ֲ���
		ConfigManager.setGlobalParams(globalParams);
		// �����û��˺���Ϣ
		setAccountInfo();
		// �Ƿ񱣳ֳ�����
		boolean isKeepAlive = true;
		// ʵ�������Ŵ������
		SmsSendConn smsSendConn = new SmsSendConn(isKeepAlive);
		// ��������
		singleSend(smsSendConn, userid, tel, code);
	}

	/**
	 * @description �����û��˺���Ϣ
	 */
	public static void setAccountInfo() {
		// �����û��˺���Ϣ
		// �û��˺�
		String userid = "E10475";
		// ����
		String password = "xNb7jT";
		// �������ȼ�
		int priority = 1;
		// ��IP��Ϣ
		String ipAddress1 = "api01.monyun.cn:7901";
		// ����IP1��Ϣ
		String ipAddress2 = "192.169.1.189:8086";
		// ����IP2��Ϣ
		String ipAddress3 = null;
		// ����IP3��Ϣ
		String ipAddress4 = null;
		// ����ֵ
		int result = -310007;
		try {
			// �����û��˺���Ϣ
			result = ConfigManager.setAccountInfo(userid, password, priority, ipAddress1, ipAddress2, ipAddress3,
					ipAddress4);
			// �жϷ��ؽ����0���óɹ�������ʧ��
			if (result == 0) {
				System.out.println("�����û��˺���Ϣ�ɹ���");
			} else {
				System.out.println("�����û��˺���Ϣʧ�ܣ������룺" + result);
			}
		} catch (Exception e) {
			// �쳣����
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @description ��������
	 * @param smsSendConn ���Ŵ������,����������е��÷��Ͷ��Ź���
	 * @param userid      �û��˺�
	 */
	public static void singleSend(SmsSendConn smsSendConn, String userid, String tel, String code) {
		try {
			// ������
			Message message = new Message();
			// �����û��˺� ָ���û��˺ŷ��ͣ���Ҫ��д�û��˺ţ���ָ���û��˺ŷ��ͣ�������д�û��˺�
			message.setUserid(userid);
			// �����ֻ����� �˴�ֻ������һ���ֻ�����
			message.setMobile(tel);
			// ��������
			message.setContent("������֤����" + code + "����10������������Ч����Ǳ��˲�������Դ˶��š�");
			// ������չ��
			message.setExno("11");
			// �û��Զ�����ˮ���
			message.setCustid("20160929194950100001");
			// �Զ�����չ����
			message.setExdata("abcdef");
			// ҵ������
			message.setSvrtype("SMS001");

			// ���ص���ˮ��
			StringBuffer returnValue = new StringBuffer();
			// ����ֵ
			int result = -310099;
			// ���Ͷ���
			result = smsSendConn.singleSend(message, returnValue);
			// resultΪ0:�ɹ�
			if (result == 0) {
				System.out.println("���������ύ�ɹ���");
				System.out.println(returnValue.toString());
			}
			// resultΪ��0��ʧ��
			else {
				System.out.println("���������ύʧ��,�����룺" + result);
			}
		} catch (Exception e) {
			// �쳣����
			e.printStackTrace();
		}
	}
}
