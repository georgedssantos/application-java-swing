package br.com.veloe.arquivo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.veloe.arquivo.model.entity.FileDetailTRN;

@Repository
public interface FileDetailTRNRepository extends JpaRepository<FileDetailTRN, Long> {
	
	List<FileDetailTRN> findByIdFile(Long ifFile);

}
