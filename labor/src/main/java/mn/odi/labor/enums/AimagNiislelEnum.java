package mn.odi.labor.enums;

public enum AimagNiislelEnum {
	ULAANBAATAR(0), ARKHANGAI(1), BAYANULGII(2), BAYANKHONGOR(3), BULGAN(4), GOVIALTAI(
			5), GOVISUMBER(6), DARKHAN(7), DORNOGOVI(8), DORNOD(9), DUNDGOVI(10), ZAVKHAN(
			11), ORKHON(12), UVURKHANGAI(13), UMNUGOVI(14), SUKHBAATAR(15), SELENGE(
			16), TUV(17), UVS(18), KHOVD(19), KHUVSGUL(20), KHENTII(21);

	private final int val;

	AimagNiislelEnum(int val) {
		this.val = val;
	}

	public int getVal() {
		return val;
	}
}
