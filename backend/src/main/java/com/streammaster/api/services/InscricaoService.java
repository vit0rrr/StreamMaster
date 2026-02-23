@Service
public class InscricaoService {

    private final InscricaoRepository repository;

    // Injeção de Dependência! O Spring entrega o repository pronto.
    public InscricaoService(InscricaoRepository repository) {
        this.repository = repository;
    }

    public void registrarNovoAluno(InscricaoRequestDTO dto) {
        // 1. Regra de negócio: Já existe?
        if (repository.existsByEmail(dto.email())) {
            throw new RuntimeException("E-mail já cadastrado no curso!");
        }

        // 2. Transforma o DTO (Maleta) na Entidade (Banco)
        Inscricao novaInscricao = new Inscricao();
        novaInscricao.setEmail(dto.email());
        novaInscricao.setDataInscricao(LocalDateTime.now());

        // 3. Manda salvar
        repository.save(novaInscricao);
    }
}