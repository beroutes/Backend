package com.beroutess.beroutess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.beroutess.beroutess.domain.Favorite;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

}
