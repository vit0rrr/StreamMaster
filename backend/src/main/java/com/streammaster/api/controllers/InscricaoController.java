@RestController
@RequestMapping("/api/inscricoes")
@CrossOrigin(origins = "*") // Permite que seu frontend (TypeScript) acesse sem erro de CORS
public class InscricaoController {

    private final InscricaoService service;

    public InscricaoController(InscricaoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> inscrever(@RequestBody InscricaoRequestDTO pedido) {
        //  anota o JSON, joga pro Chef (Service) e devolve o HTTP 201 (Sucesso)
        service.registrarNovoAluno(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body("Inscrição realizada!");
    }
}