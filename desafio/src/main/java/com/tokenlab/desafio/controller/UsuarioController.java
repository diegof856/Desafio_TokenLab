package com.tokenlab.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tokenlab.desafio.dto.LoginRequestDTO;
import com.tokenlab.desafio.dto.UsuarioRequestDTO;
import com.tokenlab.desafio.dto.UsuarioResponseDTO;
import com.tokenlab.desafio.service.UsuarioService;

@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping
	public ResponseEntity<Void> criarUsuario(@RequestBody UsuarioRequestDTO usuarioRequestDTO) {
		usuarioService.criarUsuario(usuarioRequestDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PatchMapping("/{id}")
    public ResponseEntity<Void> atualizarUsuario(@PathVariable Long id, @RequestParam(name = "senha") String senha){
		usuarioService.atualizarSenha(senha, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> removerUsuario(@PathVariable Long id){
		usuarioService.removerUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	@PostMapping("/login")
	public ResponseEntity<UsuarioResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
		 UsuarioResponseDTO usuarioResponse = usuarioService.fazerLogin(loginRequestDTO.getEmail(), loginRequestDTO.getSenha());
	        return ResponseEntity.ok(usuarioResponse); 
		
	}
}
