package br.com.veloe.arquivo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.veloe.arquivo.model.entity.FileTRN;

@Repository
public interface FileTRNRepository extends JpaRepository<FileTRN, Long> {
	
	Optional<FileTRN> findBySequencial(String sequencial);

}
