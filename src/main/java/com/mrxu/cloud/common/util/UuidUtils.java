package com.mrxu.cloud.common.util;

import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.UUID;

public abstract class UuidUtils {

	private final static char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
			'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
			'V', 'W', 'X', 'Y', 'Z' };

	public static String numericToString(long i, int system) {
		long num = 0;
		if (i < 0) {
			num = ((long) 2 * 0x7fffffff) + i + 2;
		} else {
			num = i;
		}
		char[] buf = new char[digits.length];
		int charPos = digits.length;
		while ((num / system) > 0) {
			buf[--charPos] = digits[(int) (num % system)];
			num /= system;
		}
		buf[--charPos] = digits[(int) (num % system)];
		return new String(buf, charPos, (digits.length - charPos));
	}

	public static String uuid() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

	public static String base64Uuid() {
		UUID uuid = UUID.randomUUID();
		return base64Uuid(uuid);
	}

	private static String base64Uuid(UUID uuid) {

		ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
		bb.putLong(uuid.getMostSignificantBits());
		bb.putLong(uuid.getLeastSignificantBits());

		return Base64.getUrlEncoder().encodeToString(bb.array());
	}

	public static String encodeBase64Uuid(String uuidString) {
		UUID uuid = UUID.fromString(uuidString);
		return base64Uuid(uuid);
	}

	public static String decodeBase64Uuid(String compressedUuid) {

		byte[] byUuid = Base64.getDecoder().decode(compressedUuid);

		ByteBuffer bb = ByteBuffer.wrap(byUuid);
		UUID uuid = new UUID(bb.getLong(), bb.getLong());
		return uuid.toString();
	}

	public static String base58Uuid() {
		UUID uuid = UUID.randomUUID();
		return base58Uuid(uuid);
	}

	private static String base58Uuid(UUID uuid) {

		ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
		bb.putLong(uuid.getMostSignificantBits());
		bb.putLong(uuid.getLeastSignificantBits());

		return Base58.encode(bb.array());
	}

	public static String encodeBase58Uuid(String uuidString) {
		UUID uuid = UUID.fromString(uuidString);
		return base58Uuid(uuid);
	}

	public static String decodeBase58Uuid(String base58uuid) {
		byte[] byUuid = Base58.decode(base58uuid);
		ByteBuffer bb = ByteBuffer.wrap(byUuid);
		UUID uuid = new UUID(bb.getLong(), bb.getLong());
		return uuid.toString();
	}

}