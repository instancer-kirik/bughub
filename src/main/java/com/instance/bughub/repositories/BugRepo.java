package com.instance.bughub.repositories;

import com.instance.bughub.entities.Bug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BugRepo extends JpaRepository<Bug,Integer> {
}