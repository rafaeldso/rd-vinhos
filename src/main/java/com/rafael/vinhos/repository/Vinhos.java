package com.rafael.vinhos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafael.vinhos.model.Vinho;

public interface Vinhos extends JpaRepository<Vinho, Long> {

}
