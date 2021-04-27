package br.com.veloe.arquivo.controller;

import java.io.IOException;
import java.nio.file.Path;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.veloe.arquivo.service.FileService;

@Controller
@RequestMapping("/files-TRN")
public class FileUploadController {
	
	private static final String PAGE_FILE_TRN = "pages/files-trn";

	@Autowired
	private FileService fileService;
	
	@Autowired
	private BuildProperties buildProperties;
	
		
	@RequestMapping 
    public String search(HttpServletRequest request, Model model) {
		
		model.addAttribute("files", this.fileService.list());
        
        return PAGE_FILE_TRN;
    }
	
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes) {
    	    	    	
    	String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        // check if file is empty
        if (file.isEmpty()) {
            attributes.addFlashAttribute("messageWarning", "Selecione um arquivo para enviar.");
            return "redirect:/files-TRN";
        }
        
        // check if file is TRN
        if(!org.springframework.util.StringUtils.getFilenameExtension(file.getOriginalFilename()).equalsIgnoreCase("TRN")) {
            attributes.addFlashAttribute("messageWarning", "Extensão inválida para o arquivo " + fileName + ". Somente arquivo TRN é suportado.");
            return "redirect:/files-TRN";
        }

		try {
			Path path = this.fileService.upload(file);
			this.fileService.readFileTRN(path);
		} catch (FileSizeLimitExceededException | MaxUploadSizeExceededException e) {
			attributes.addFlashAttribute("messageError", "O tamanho do arquivo excede o limite!");
		} catch (IOException e) {
			attributes.addFlashAttribute("messageError", "Erro: " + e.getMessage() + '!');
		}
        
        attributes.addFlashAttribute("message", "Upload feito com sucesso! " + fileName + '!');
        
        return "redirect:/files-TRN";
    }
    

	@ModelAttribute("version")
	public String version() {
		return buildProperties.getVersion();
	}

}