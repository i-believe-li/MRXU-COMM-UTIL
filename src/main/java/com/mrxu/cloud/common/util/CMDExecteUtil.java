package com.mrxu.cloud.common.util;

import com.mrxu.cloud.common.enums.MrxuExceptionEnums;
import com.mrxu.cloud.common.exception.MrxuException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CMDExecteUtil {

	Logger logger = LoggerFactory.getLogger(CMDExecteUtil.class);

	public String exec(String command) {
		try {
			Process p = Runtime.getRuntime().exec(command);
			StreamCaptureThread errorStream = new StreamCaptureThread(p.getErrorStream());
			StreamCaptureThread outputStream = new StreamCaptureThread(p.getInputStream());
			new Thread(errorStream).start();
			new Thread(outputStream).start();
			p.waitFor();
			String result = command + "\n" + outputStream.output.toString() + errorStream.output.toString();
			logger.info(result);

			if (!StringUtils.isEmpty(errorStream.output.toString())) {
				return errorStream.output.toString();
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new MrxuException(MrxuExceptionEnums.RC_COMMON_ERROR.getCode(), e.getMessage());
		}
		return null;
	}

	private class StreamCaptureThread implements Runnable {
		InputStream stream;
		StringBuilder output;

		public StreamCaptureThread(InputStream stream) {
			this.stream = stream;
			this.output = new StringBuilder();
		}

		public void run() {
			try {
				try {
					BufferedReader br = new BufferedReader(new InputStreamReader(this.stream));
					String line = br.readLine();
					while (line != null) {
						if (line.trim().length() > 0) {
							output.append(line).append("\n");
						}
						line = br.readLine();
					}
				} finally {
					if (stream != null) {
						stream.close();
					}
				}
			} catch (IOException ex) {
				ex.printStackTrace(System.err);
			}
		}
	}
}
