package mvc.upload.domain;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

//업로드되는 여러개의 파일들의 정보를 가지는 클래스
public class FormData {

	private List<MultipartFile> files;
	
	public FormData() {
		
	}

	public FormData(List<MultipartFile> files) {
		super();
		this.files = files;
	}

	public List<MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}

	@Override
	public String toString() {
		return "FormData [files=" + files + "]";
	}
	
	
	
}
