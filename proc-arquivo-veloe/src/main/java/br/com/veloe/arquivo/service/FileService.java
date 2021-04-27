package br.com.veloe.arquivo.service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import br.com.veloe.arquivo.model.entity.FileTRN;

public interface FileService {
	
	Path upload(MultipartFile file) throws FileSizeLimitExceededException, MaxUploadSizeExceededException, IOException;	
	
	void readFileTRN(Path path) throws IOException;
	
	FileTRN save(FileTRN fileTRN);
	
	List<FileTRN> list();

}
