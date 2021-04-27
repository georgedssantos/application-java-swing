package br.com.veloe.arquivo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import br.com.veloe.arquivo.model.entity.FileTRN;
import br.com.veloe.arquivo.repository.FileTRNRepository;

@Service
public class FileServiceImpl implements FileService {
	
	@Autowired
	private FileTRNRepository fileTRNRepository;
	
	private final String UPLOAD_DIR = new ClassPathResource("/src/main/resources/uploads/").getPath();

	@Override
	public Path upload(MultipartFile file) throws FileSizeLimitExceededException, MaxUploadSizeExceededException, IOException {		
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path path = Paths.get(UPLOAD_DIR + fileName);
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        return path;
	}

	@Override
	public void readFileTRN(Path path) throws IOException {
        List<String> rows = Files.readAllLines(path);
        
        for (String row : rows) {
        	FileTRN file = new FileTRN();
        	
        	String tipo = row.substring(0,2);
            System.out.println("Tipo: "+tipo);
            file.setTipo(tipo);
            
        	String pais = row.substring(2,6);
            System.out.println("Pais: "+pais);
            
            String idConcessionaria = row.substring(6,11);
            System.out.println("idConcessionaria: "+idConcessionaria);
            file.setIdConcessionaria(idConcessionaria);
            
            String sequencial = row.substring(11,16);
            System.out.println("Sequencial: "+sequencial);
            file.setSequencial(sequencial);
            
            String dataGeracao = row.substring(16,24);
            System.out.println("DataGeracao: "+dateFormat(dataGeracao));
            file.setDataGeracao(dateFormat(dataGeracao));
            
            String horaGeracao = row.substring(24,30);
            System.out.println("HoraGeracao: "+timeFormat(horaGeracao));
            file.setHoraGeracao(timeFormat(horaGeracao));
            
            String totalRegistro = row.substring(30,36);
            System.out.println("TotalRegistro: "+totalRegistro);
            file.setTotalRegistro(totalRegistro);
            
            String valorTotal = row.substring(36,48);
            valorTotal = valorTotal.replaceAll("^0*", "");            
            valorTotal = valorTotal.substring(0, valorTotal.length()-2) + "," + valorTotal.substring(valorTotal.length()-2);
            System.out.println("valorTotal: "+valorTotal);
            file.setValorTotal(valorTotal);
            
            save(file);
            
            break;
         
        }

	}
	
	@Override
	public FileTRN save(FileTRN fileTRN) {
		fileTRN.setId(null);
		return this.fileTRNRepository.save(fileTRN);
	}
	
	@Override
	public List<FileTRN> list() {
		return this.fileTRNRepository.findAll();
	}

	public String dateFormat(String date) {
		LocalDate dateTime = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd"));		
		return dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
	
	public String timeFormat(String time) {
		LocalTime localTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HHmmss"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
		return formatter.format(localTime);
	}

}
