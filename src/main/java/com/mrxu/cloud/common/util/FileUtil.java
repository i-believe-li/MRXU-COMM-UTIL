package com.mrxu.cloud.common.util;

import com.mrxu.cloud.common.enums.MrxuExceptionEnums;
import com.mrxu.cloud.common.exception.MrxuException;
import com.mrxu.cloud.common.util.md5.MD5;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import java.util.zip.*;

public class FileUtil {

	public static String getFileExtName(String fileName) {

		if (fileName.contains(".")) {
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		}

		return null;
	}

	public static String getFilePath(String fileName) {
		fileName = fileName.replaceAll("\\\\", "/");
		if (fileName.contains("/")) {
			return fileName.substring(0, fileName.lastIndexOf("/") + 1);
		}

		return null;
	}

	public static String getFileName(String fileName) {
		fileName = fileName.replaceAll("\\\\", "/");
		if (fileName.contains("/")) {
			return fileName.substring(fileName.lastIndexOf("/") + 1);
		}

		return null;
	}
	
	
	public static String getFileNameWithoutPath(String fileName) {
		fileName = fileName.replaceAll("\\\\", "/");
		if (fileName.contains("/")) {
			fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
		}
		if (fileName.contains(".")) {
			return fileName.substring(0, fileName.lastIndexOf("."));
		}
		return fileName;
	}

	public static String getFileNameWithoutSuffix(String fileName) {
		fileName = fileName.replaceAll("\\\\", "/");
		if (fileName.contains(".")) {
			return fileName.substring(0, fileName.lastIndexOf("."));
		}

		return null;
	}

	/**
	 * 解压到指定目录
	 * 
	 * @param zipPath
	 * @param descDir
	 */
	public static void unZipFiles(String zipPath, String descDir) throws IOException {
		unZipFiles(new File(zipPath), descDir);
	}

	/**
	 * 解压文件到指定目录 解压后的文件名，和之前一致
	 * 
	 * @param zipFile
	 *            待解压的zip文件
	 * @param descDir
	 *            指定目录
	 */
	public static void unZipFiles(File zipFile, String descDir) throws IOException {
		ZipFile zip = new ZipFile(zipFile, Charset.forName("GBK"));// 解决中文文件夹乱码 ,

		File pathFile = new File(descDir);
		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}

		for (Enumeration<? extends ZipEntry> entries = zip.entries(); entries.hasMoreElements();) {
			ZipEntry entry = (ZipEntry) entries.nextElement();
			String zipEntryName = entry.getName();
			InputStream in = zip.getInputStream(entry);
			String outPath = (descDir + "/" + zipEntryName).replaceAll("\\*", "/");

			// 判断路径是否存在,不存在则创建文件路径
			File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
			if (!file.exists()) {
				file.mkdirs();
			}
			// 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
			if (new File(outPath).isDirectory()) {
				continue;
			}
			// 输出文件路径信息
			// System.out.println(outPath);

			FileOutputStream out = new FileOutputStream(outPath);
			byte[] buf1 = new byte[1024];
			int len;
			while ((len = in.read(buf1)) > 0) {
				out.write(buf1, 0, len);
			}
			in.close();
			out.close();
		}
		System.out.println("******************解压完毕********************");
		return;
	}

	/**
	 * 递归压缩文件夹
	 * 
	 * @param srcRootDir
	 *            压缩文件夹根目录的子路径
	 * @param file
	 *            当前递归压缩的文件或目录对象
	 * @param zos
	 *            压缩文件存储对象
	 * @throws Exception
	 */
	private static void zip(String srcRootDir, File file, ZipOutputStream zos) throws Exception {
		if (file == null) {
			return;
		}

		// 如果是文件，则直接压缩该文件
		if (file.isFile()) {
			int count, bufferLen = 1024;
			byte data[] = new byte[bufferLen];

			// 获取文件相对于压缩文件夹根目录的子路径
			String subPath = file.getAbsolutePath();
			int index = subPath.indexOf(srcRootDir);
			if (index != -1) {
				subPath = subPath.substring(srcRootDir.length() + File.separator.length());
			}
			ZipEntry entry = new ZipEntry(subPath);
			zos.putNextEntry(entry);
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			while ((count = bis.read(data, 0, bufferLen)) != -1) {
				zos.write(data, 0, count);
			}
			bis.close();
			zos.closeEntry();
		}
		// 如果是目录，则压缩整个目录
		else {
			// 压缩目录中的文件或子目录
			File[] childFileList = file.listFiles();
			for (int n = 0; n < childFileList.length; n++) {
				childFileList[n].getAbsolutePath().indexOf(file.getAbsolutePath());
				zip(srcRootDir, childFileList[n], zos);
			}
		}
	}

	/**
	 * 对文件或文件目录进行压缩
	 * 
	 * @param srcPath
	 *            要压缩的源文件路径。如果压缩一个文件，则为该文件的全路径；如果压缩一个目录，则为该目录的顶层目录路径
	 * @param zipPath
	 *            压缩文件保存的路径。注意：zipPath不能是srcPath路径下的子文件夹
	 * @param zipFileName
	 *            压缩文件名
	 * @throws Exception
	 */
	public static void zip(String srcPath, String zipPath, String zipFileName) throws Exception {
		if (StringUtils.isEmpty(srcPath) || StringUtils.isEmpty(zipPath) || StringUtils.isEmpty(zipFileName)) {
			throw new MrxuException(MrxuExceptionEnums.RC_COMMON_ERROR);
		}
		CheckedOutputStream cos = null;
		ZipOutputStream zos = null;
		try {
			File srcFile = new File(srcPath);

			// 判断压缩文件保存的路径是否为源文件路径的子文件夹，如果是，则抛出异常（防止无限递归压缩的发生）
			if (srcFile.isDirectory() && zipPath.indexOf(srcPath) != -1) {
				throw new MrxuException(MrxuExceptionEnums.RC_COMMON_ERROR);
			}

			// 判断压缩文件保存的路径是否存在，如果不存在，则创建目录
			File zipDir = new File(zipPath);
			if (!zipDir.exists() || !zipDir.isDirectory()) {
				zipDir.mkdirs();
			}

			// 创建压缩文件保存的文件对象
			String zipFilePath = zipPath + File.separator + zipFileName;
			File zipFile = new File(zipFilePath);
			if (zipFile.exists()) {
				// 检测文件是否允许删除，如果不允许删除，将会抛出SecurityException
				SecurityManager securityManager = new SecurityManager();
				securityManager.checkDelete(zipFilePath);
				// 删除已存在的目标文件
				zipFile.delete();
			}

			cos = new CheckedOutputStream(new FileOutputStream(zipFile), new CRC32());
			zos = new ZipOutputStream(cos);

			// 如果只是压缩一个文件，则需要截取该文件的父目录
			String srcRootDir = srcPath;
			if (srcFile.isFile()) {
				int index = srcPath.lastIndexOf(File.separator);
				if (index != -1) {
					srcRootDir = srcPath.substring(0, index);
				}
			}
			// 调用递归压缩方法进行目录或文件压缩
			zip(srcRootDir, srcFile, zos);
			zos.flush();
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (zos != null) {
					zos.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 解压缩zip包
	 * 
	 * @param zipFilePath
	 *            zip文件的全路径
	 * @param unzipFilePath
	 *            解压后的文件保存的路径
	 * @param includeZipFileName
	 *            解压后的文件保存的路径是否包含压缩文件的文件名。true-包含；false-不包含
	 */
	@SuppressWarnings("unchecked")
	public static void unzip(String zipFilePath, String unzipFilePath, boolean includeZipFileName) throws Exception {
		if (StringUtils.isEmpty(zipFilePath) || StringUtils.isEmpty(unzipFilePath)) {
			throw new MrxuException(MrxuExceptionEnums.RC_COMMON_ERROR);
		}
		File zipFile = new File(zipFilePath);
		// 如果解压后的文件保存路径包含压缩文件的文件名，则追加该文件名到解压路径
		if (includeZipFileName) {
			String fileName = zipFile.getName();
			if (!StringUtils.isEmpty(fileName)) {
				fileName = fileName.substring(0, fileName.lastIndexOf("."));
			}
			unzipFilePath = unzipFilePath + File.separator + fileName;
		}
		// 创建解压缩文件保存的路径
		File unzipFileDir = new File(unzipFilePath);
		if (!unzipFileDir.exists() || !unzipFileDir.isDirectory()) {
			unzipFileDir.mkdirs();
		}

		// 开始解压
		ZipEntry entry = null;
		String entryFilePath = null, entryDirPath = null;
		File entryFile = null, entryDir = null;
		int index = 0, count = 0, bufferSize = 1024;
		byte[] buffer = new byte[bufferSize];
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		ZipFile zip = new ZipFile(zipFile);
		Enumeration<ZipEntry> entries = (Enumeration<ZipEntry>) zip.entries();
		// 循环对压缩包里的每一个文件进行解压
		while (entries.hasMoreElements()) {
			entry = entries.nextElement();
			// 构建压缩包中一个文件解压后保存的文件全路径
			entryFilePath = unzipFilePath + File.separator + entry.getName();
			// 构建解压后保存的文件夹路径
			index = entryFilePath.lastIndexOf(File.separator);
			if (index != -1) {
				entryDirPath = entryFilePath.substring(0, index);
			} else {
				entryDirPath = "";
			}
			entryDir = new File(entryDirPath);
			// 如果文件夹路径不存在，则创建文件夹
			if (!entryDir.exists() || !entryDir.isDirectory()) {
				entryDir.mkdirs();
			}

			// 创建解压文件
			entryFile = new File(entryFilePath);
			if (entryFile.exists()) {
				// 检测文件是否允许删除，如果不允许删除，将会抛出SecurityException
				SecurityManager securityManager = new SecurityManager();
				securityManager.checkDelete(entryFilePath);
				// 删除已存在的目标文件
				entryFile.delete();
			}

			// 写入文件
			bos = new BufferedOutputStream(new FileOutputStream(entryFile));
			bis = new BufferedInputStream(zip.getInputStream(entry));
			while ((count = bis.read(buffer, 0, bufferSize)) != -1) {
				bos.write(buffer, 0, count);
			}
			bos.flush();
			bos.close();
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param path
	 */
	public static void deleteFile(Path path) {
		try {
			Files.delete(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String readFile(String filePath) {
		// 定义路径
		Path path = Paths.get(filePath);
		String content = "";
		// 通过Files.lines 读取 返回 Stream<Stream>
		try (Stream<String> lines = Files.lines(path, Charset.defaultCharset())) {
			// 使用StreamAPI的归约操作 reduce(初始值，BinaryOperator<T>)
			// BinaryOperator<T> (T,T)->T 将两个元素结合起来产生一个新值
			// 这里我们用的是lambda表达式 (a,b)->a+b
			content = lines.reduce("", (line1, line2) -> line1 + line2);
		} catch (NoSuchFileException e) {
			System.out.println("找不到指定文件");
		} catch (IOException e) {
			System.out.println("读取文件出错！");
			e.printStackTrace();
		}
		System.out.println(content + "      --------- content    --- TestStream.main\n");
		return content;
	}

	/**
	 * 删除文件，可以是文件或文件夹
	 *
	 * @param fileName
	 *            要删除的文件名
	 * @return 删除成功返回true，否则返回false
	 */
	public static boolean delete(String fileName) {
		File file = new File(fileName);
		if (!file.exists()) {
			System.out.println("删除文件失败:" + fileName + "不存在！");
			return false;
		} else {
			if (file.isFile())
				return deleteFile(fileName);
			else
				return deleteDirectory(fileName);
		}
	}

	/**
	 * 删除单个文件
	 *
	 * @param fileName
	 *            要删除的文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				System.out.println("删除单个文件" + fileName + "成功！");
				return true;
			} else {
				System.out.println("删除单个文件" + fileName + "失败！");
				return false;
			}
		} else {
			System.out.println("删除单个文件失败：" + fileName + "不存在！");
			return false;
		}
	}

	/**
	 * 删除目录及目录下的文件
	 *
	 * @param dir
	 *            要删除的目录的文件路径
	 * @return 目录删除成功返回true，否则返回false
	 */
	public static boolean deleteDirectory(String dir) {
		// 如果dir不以文件分隔符结尾，自动添加文件分隔符
		if (!dir.endsWith(File.separator))
			dir = dir + File.separator;
		File dirFile = new File(dir);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
			System.out.println("删除目录失败：" + dir + "不存在！");
			return false;
		}
		boolean flag = true;
		// 删除文件夹中的所有文件包括子目录
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
			// 删除子目录
			else if (files[i].isDirectory()) {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag) {
			System.out.println("删除目录失败！");
			return false;
		}
		// 删除当前目录
		if (dirFile.delete()) {
			System.out.println("删除目录" + dir + "成功！");
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 生成.json格式文件
	 * 
	 * @param jsonString
	 *            json内容
	 * @param savePath
	 *            文件生成路径
	 * @param fileName
	 *            文件名
	 * @return
	 */
	public static boolean createJsonFile(String jsonString, String savePath, String fileName) {
		// 标记文件生成是否成功
		boolean flag = true;
		// 拼接文件完整路径
		String fullPath = savePath + File.separator + fileName + ".json";
		FileOutputStream out = null;
		// 生成json格式文件
		try {
			// 保证创建一个新文件
			File file = new File(fullPath);
			if (!file.getParentFile().exists()) { // 如果父目录不存在，创建父目录
				file.getParentFile().mkdirs();
			}
			if (file.exists()) { // 如果已存在,删除旧文件
				file.delete();
			}
			file.createNewFile();

			// 格式化json字符串
			// jsonString = JSONObject.toJSONString(jsonString);
			// 将格式化后的字符串写入文件
			out = new FileOutputStream(file);
			Writer write = new OutputStreamWriter(out, "UTF-8");
			write.write(jsonString);
			write.flush();
			write.close();
		} catch (Exception e) {
			flag = false;
		} finally {
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		// 返回是否成功的标记
		return flag;
	}

	/**
	 * 从给定位置读取Json文件
	 * 
	 * @param path
	 * @return
	 */
	public static String readJson(String path) throws MrxuException {
		// 从给定位置获取文件
		File file = new File(path);
		// 判断文件是否存在
		if (!file.exists()) {
			return null;
		}
		BufferedReader reader = null;
		// 返回值,使用StringBuffer
		StringBuffer data = new StringBuffer();
		try {
			reader = new BufferedReader(new FileReader(file));
			// 每次读取文件的缓存
			String temp;
			while ((temp = reader.readLine()) != null) {
				data.append(temp);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭文件流
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return data.toString();
	}

	/**
	 * 创建目录
	 * 
	 * @param destDirName
	 * @return
	 */
	public static boolean createDir(String destDirName) {
		File dir = new File(destDirName);
		if (dir.exists()) {
			return false;
		}
		if (!destDirName.endsWith(File.separator)) {
			destDirName = destDirName + File.separator;
		}
		// 创建目录
		if (dir.mkdirs()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断文件夹是否存在
	 * 
	 * @param file
	 * @return
	 */
	public static boolean judeDirExists(File file) {
		if (file.exists()) {
			System.out.println("文件存在！");
			// 文件存在
			if (file.isDirectory()) {
				// 是目录
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	/**
	 * 生成临时打包目录树
	 * 
	 * @param rootDirName
	 * @param uniqueCode
	 * @return
	 */
	public static Map<String, String> generateSceneTempPackDirTree(String rootDirName, String uniqueCode) {
		Map<String, String> result = new HashMap<>();
		String packDirName = rootDirName + uniqueCode; // 生成当前打包根目录
		File rootDir = new File(packDirName);
		boolean isExist = judeDirExists(rootDir);
		if (!isExist) {
			if (createDir(packDirName)) {
				System.out.println("打包根创建成功！");
			} else {
				return null;
			}
		}
		// 生成存放静态资源目录
		String resourcesDirName = packDirName + "/resources/";
		File resourcesDir = new File(resourcesDirName);
		boolean isResourcesDirExist = judeDirExists(resourcesDir);
		if (!isResourcesDirExist) {
			if (!createDir(resourcesDirName)) {
				return null;
			}
		}
		// 生成存放静态资源--模型目录
		String modelDirName = resourcesDirName + "model/";
		File modelDir = new File(modelDirName);
		boolean isModelDirExist = judeDirExists(modelDir);
		if (!isModelDirExist) {
			if (!createDir(modelDirName)) {
				return null;
			}
		}
		// 模块初始化加载资源目录
		String templateResourceDirName = resourcesDirName + "templateResource/";
		File templateResourceDir = new File(templateResourceDirName);
		boolean isTemplateResourceDir = judeDirExists(templateResourceDir);
		if (!isTemplateResourceDir) {
			if (!createDir(templateResourceDirName)) {
				return null;
			}
		}
		// 模块详细配置静态资源目录
		String templateConfigDirName = resourcesDirName + "templateConfig/";
		File templateConfigDir = new File(templateConfigDirName);
		boolean isTemplateConfigDir = judeDirExists(templateConfigDir);
		if (!isTemplateConfigDir) {
			if (!createDir(templateConfigDirName)) {
				return null;
			}
		}
		// 配置中动画MP3播放资源
		String templateAniResourcesDirName = resourcesDirName + "templateAniResources/";
		File templateAniResourcesDir = new File(templateConfigDirName);
		boolean isTemplateAniResourcesDir = judeDirExists(templateAniResourcesDir);
		if (!isTemplateAniResourcesDir) {
			if (!createDir(templateAniResourcesDirName)) {
				return null;
			}
		}

		result.put("packDir", packDirName); // 打包目录
		result.put("resourcesDir", resourcesDirName); // 静态资源目录
		result.put("modelDir", modelDirName); // 静态资源模块目录
		result.put("templateResourceDir", templateResourceDirName); // 模块初始化加载静态资源目录
		result.put("templateConfigDir", templateConfigDirName); // 模块详细配置静态资源目录
		result.put("templateAniResourcesDir", templateAniResourcesDirName); // 配置中动画MP3播放资源
		return result;
	}

	/**
	 * 生成临时打包目录树
	 * 
	 * @param rootDirName
	 * @param uniqueCode
	 * @return
	 */
	public static Map<String, String> generateScannerTempPackDirTree(String rootDirName, String uniqueCode) {

		Map<String, String> result = new HashMap<>();
		String packDirName = rootDirName + uniqueCode; // 生成当前打包根目录
		File rootDir = new File(packDirName);
		boolean isExist = judeDirExists(rootDir);
		if (!isExist) {
			if (createDir(packDirName)) {
				System.out.println("打包根创建成功！");
			} else {
				return null;
			}
		}
		// 生成存放静态资源目录
		String resourcesDirName = packDirName + "/resources/";
		File resourcesDir = new File(resourcesDirName);
		boolean isResourcesDirExist = judeDirExists(resourcesDir);
		if (!isResourcesDirExist) {
			if (!createDir(resourcesDirName)) {
				return null;
			}
		}
		// 生成存放静态资源--模型目录
		String funcRuleDirName = resourcesDirName + "funcRule/";
		File funcRuleDir = new File(funcRuleDirName);
		boolean isModelDirExist = judeDirExists(funcRuleDir);
		if (!isModelDirExist) {
			if (!createDir(funcRuleDirName)) {
				return null;
			}
		}
		// 模块初始化加载资源目录
		String scanPicsDirName = resourcesDirName + "scanPics/";
		File scanPicsDir = new File(scanPicsDirName);
		boolean isScanPicsDir = judeDirExists(scanPicsDir);
		if (!isScanPicsDir) {
			if (!createDir(scanPicsDirName)) {
				return null;
			}
		}
		result.put("packDir", packDirName); // 打包目录
		result.put("resourcesDir", resourcesDirName); // 静态资源目录
		result.put("funcRuleDir", funcRuleDirName); // 静态资源模块目录
		result.put("scanPicsDir", scanPicsDirName); // 模块初始化加载静态资源目录
		return result;
	}

	/**
	 * 从网络Url中下载文件
	 * 
	 * @param urlStr
	 * @param fileName
	 * @param savePath
	 * @throws IOException
	 */
	public static void downLoadFromUrl(String urlStr, String fileName, String savePath) throws MrxuException {
		URL url;
		FileOutputStream fos = null;
		InputStream inputStream = null;
		try {
			url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 设置超时间为3秒
			conn.setConnectTimeout(3 * 1000);
			// 防止屏蔽程序抓取而返回403错误
			conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

			// 得到输入流
			inputStream = conn.getInputStream();
			// 获取自己数组
			byte[] getData = FileUtil.readInputStream(inputStream);

			// 文件保存位置
			File saveDir = new File(savePath);
			if (!saveDir.exists()) {
				saveDir.mkdir();
			}
			File file = new File(saveDir + File.separator + fileName);
			fos = new FileOutputStream(file);
			fos.write(getData);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("info:" + urlStr + " download fail");
			throw new MrxuException(MrxuExceptionEnums.RC_COMMON_ERROR);
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					throw new MrxuException(MrxuExceptionEnums.RC_COMMON_ERROR);
				}
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					throw new MrxuException(MrxuExceptionEnums.RC_COMMON_ERROR);
				}
			}
		}
		System.out.println("info:" + url + " download success");
	}

	/**
	 * 从输入流中获取字节数组
	 * 
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public static byte[] readInputStream(InputStream inputStream) throws IOException {
		byte[] buffer = new byte[1024];
		int len = 0;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while ((len = inputStream.read(buffer)) != -1) {
			bos.write(buffer, 0, len);
		}
		bos.close();
		return bos.toByteArray();
	}

	/**
	 * 计算文件MD5
	 * @param file
	 * @param delete
	 * @return
	 * @throws MrxuException
	 */
	public static String calculateFileMD5(File file, boolean delete) throws MrxuException{
		if(file.exists()){
			try {
				return MD5.asHex(MD5.getHash(file));
			} catch (IOException e) {
				throw new MrxuException(MrxuExceptionEnums.RC_COMMON_ERROR, "无法计算文件MD5值，请核对！");
			} finally {
				if(delete){
					file.delete();
				}
			}
		} else {
			throw new MrxuException(MrxuExceptionEnums.RC_COMMON_ERROR);
		}
	}
}
