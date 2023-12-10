package com.idstar.lat5.repository;


import com.idstar.lat5.model.Rekening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RekeningRepository extends JpaRepository<Rekening,Long> {

    @Query("select c from Rekening c WHERE c.id = :id")
    public Rekening getbyID(@Param("id") Long id);
}
