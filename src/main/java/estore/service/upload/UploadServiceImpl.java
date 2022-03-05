package estore.service.upload;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadServiceImpl implements UploadService {
	@Autowired
	ServletContext app;
	
	@Override
	public File save(MultipartFile uploadFile, String storageFolder) {
		File dir = this.getFile(storageFolder);
		try {
			File savedFile = new File(dir, getUniqueFilename(uploadFile));
			uploadFile.transferTo(savedFile);
			return savedFile;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<File> save(MultipartFile[] uploadFiles, String storageFolder) {
		List<File> list = new ArrayList<>();
		for(MultipartFile uploadFile: uploadFiles) {
			File savedFile = this.save(uploadFile, storageFolder);
			if(savedFile != null) {
				list.add(savedFile);
			}
		}
		return list;
	}

	@Override
	public void delete(String filename, String storageFolder) {
		File dir = this.getFile(storageFolder);
		new File(dir, filename).delete();
	}
	
	@Override
	public File getFile(String virtualPath) {
		File file = new File(app.getRealPath(virtualPath));
		// tạo thư mục mới nếu chưa tồn tại
		if(!file.exists()) { 
			file.mkdirs();
		}
		return file;
	}
	
	String getUniqueFilename(MultipartFile uploadFile) {
		String f = System.currentTimeMillis() + uploadFile.getOriginalFilename();
		return Integer.toHexString(f.hashCode()) + f.substring(f.lastIndexOf("."));
	}
}
