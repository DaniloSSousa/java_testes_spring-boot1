package br.com.alura.forum.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.controller.dto.TopicoDtoDetailed;
import br.com.alura.forum.controller.dto.TopicoDtoReceiver;
import br.com.alura.forum.controller.dto.TopicoDtoReceiverPut;
import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.modelo.Usuario;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;
import br.com.alura.forum.repository.UsuarioRepository;

@RestController
@RequestMapping("/topicos")
public class TopicosController {
    @Autowired
    private TopicoRepository topicoRepository;  
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    // Classe receiver utilizada para receber os campos necessários q serão criados no banco, demais campos faltantes
    // serão fornecidos pela classe de domínio do recurso correspondente ao ser feita a conversão
    @PostMapping
    @Transactional
    public ResponseEntity<TopicoDto> create(@RequestBody @Valid TopicoDtoReceiver tdr, UriComponentsBuilder ucb) {    	
    	Curso c = cursoRepository.findByNome(tdr.getCurso());
    	//Faltou tratar erro no campo autorId para notFound e usar .findById()
    	Usuario u = usuarioRepository.getOne(tdr.getAutorId());
    	Topico t = Topico.forTopico(tdr, c, u);

    	topicoRepository.save(t);

    	URI uri = ucb.path("/topicos/{id}").buildAndExpand(t.getId()).toUri();
    	
    	// ResponseEntity.created retorna o código 201
    	return ResponseEntity.created(uri).body(new TopicoDto(t));
    }
    
    @GetMapping
    public List<TopicoDto> read(String curso) {
    	if (curso == null) {
    		List<Topico> l = topicoRepository.findAll();
    		    		
    		return TopicoDto.forListDto(l);	
    	}
    	
    	List<Topico> l = topicoRepository.findByCurso_Nome(curso);
    		
    	return TopicoDto.forListDto(l);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<TopicoDtoDetailed> readById(@PathVariable Long id) {
    	Optional<Topico> o = topicoRepository.findById(id);
    	
    	if (!o.isPresent()) return ResponseEntity.notFound().build();
    	
    	return ResponseEntity.ok(new TopicoDtoDetailed(o.get()));
    }
    
    
    // Foi utilizada uma nova classe receiver para limitar o número de campos q podem ser atualizados
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDto> update(@PathVariable Long id, @RequestBody @Valid TopicoDtoReceiverPut tdrp) {
    	Optional<Topico> o = topicoRepository.findById(id);

    	if (!o.isPresent()) return ResponseEntity.notFound().build();
    	
    	Topico t = o.get();
    	
    	t.setTitulo(tdrp.getTitulo());
    	t.setMensagem(tdrp.getMensagem());
    	
    	// ResponseEntity.ok() retorna o código 200 junto com o body
    	return ResponseEntity.ok(TopicoDto.forTopicoDto(t));
    }
    
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {
    	Optional<Topico> o = topicoRepository.findById(id);

    	if (!o.isPresent()) return ResponseEntity.notFound().build();
    	
    	topicoRepository.deleteById(id);
    	
    	return ResponseEntity.ok().build();
    }
}
