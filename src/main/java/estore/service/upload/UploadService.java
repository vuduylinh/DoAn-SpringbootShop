package estore.service.upload;

import java.io.File;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
	/**
	 * Lưu file upload vào thư mục với tên duy nhất được sinh tự động
	 * @param uploadFile là file upload
	 * @param storageFolder thư mục lưu file upload
	 * @return File đã lưu hoặc null
	 */
	File save(MultipartFile uploadFile, String storageFolder);
	/**
	 * Lưu các file upload vào thư mục. Tên mỗi file duy nhất được sinh tự động
	 * @param uploadFiles là mảng các file upload
	 * @param storageFolder thư mục lưu các file upload
	 * @return Danh sách file đã lưu
	 */
	List<File> save(MultipartFile[] uploadFiles, String storageFolder);
	/**
	 * Xóa file trong thư mục
	 * @param filename tên file
	 * @param storageFolder tên thư mục chứa file
	 */
	void delete(String filename, String storageFolder);
	/**
	 * Tạo File từ đường dẫn ảo
	 * @param virtualDir đường dẫn ảo (tính từ gốc website)
	 * @return đối tượng file
	 */
	File getFile(String virtualPath);
}
