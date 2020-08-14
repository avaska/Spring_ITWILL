package store;

/*
ActionForward Ŭ������ Action �������̽��� ������ �ڽİ�ü�� ����� �����ϰ�
 ������� ������ �������� ������(���û)�Ҷ� ���Ǵ� Ŭ�����̴�
	�ϴ���1. ������ �̵���� ���ΰ��� ������ ��ȯ���ش�
		- ������ �̵���� ���ΰ� true �϶� => response.rendredirect();�����̷�Ʈ���
		- ������ �̵���� ���ΰ� false�϶� => forward(); ����ġ ������� ���� 		 
	�ϴ���2. �̵��� ������ ��� �ּҰ��� �����Ͽ� ��ȯ���ִ� ����.
*/

public class ActionForward {
	// ������ �̵���� ���ΰ��� ������ ����
	private boolean isRedirect=false;	
	// �̵��� ������ ��� �ּҰ��� ������ ����
	private String path=null;
	
	// ������ �̵���� ���ΰ��� ������ �޼ҵ�
	public void setRedirect(boolean isRedirect){
		this.isRedirect=isRedirect;	}
	
	// ������ �̵���� ���ΰ��� �������ִ� �޼ҵ�
	public boolean isRedirect(){
		return isRedirect;	}
	
	// �̵��� ������ ��� �ּҰ��� �������ִ� �޼ҵ�
	public String getPath() {
		return path;	}

	// �̵��� ������ ��� �ּҰ��� ������ �޼ҵ�
	public void setPath(String path) {
		this.path = path;	}

}// Ŭ���� ����

