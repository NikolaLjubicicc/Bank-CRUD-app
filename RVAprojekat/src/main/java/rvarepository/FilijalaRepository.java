package rvarepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rva.models.Banka;
import rva.models.Filijala;
@Repository
public interface FilijalaRepository  extends JpaRepository<Filijala, Integer>{
	List<Filijala> findByPosedujeSefEquals(boolean poseduje_sef);
	
	List<Filijala> findByBanka(Banka banka);
}
