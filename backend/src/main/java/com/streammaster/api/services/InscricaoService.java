@Service
public class InscricaoService {

    private final InscricaoRepository Repository;

    public InscricaoService(InscricaoRepository Repository) {
        this.Repository = Repository;
    }

    public Inscricao salvar(Inscricao inscricao) {
        return Repository.save(inscricao);
    }


    public lista<Inscricao> listar() {
        return Repository.findAll();
    }

    public Inscricao buscarPorId(Long id) {
        return Repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Inscrição não encontrada com id: " + id));
    }

    public Inscricao atualizar(Long id, Inscricao dadosAtualizados) {
        Inscricao inscricaoExistente = buscarPorId(id);
        // Atualize os campos necessários
        inscricaoExistente.setCampo1(dadosAtualizados.getCampo1());
        inscricaoExistente.setCampo2(dadosAtualizados.getCampo2());
        // ... atualize outros campos conforme necessário
        return Repository.save(inscricaoExistente);
    }

    public void deletar(Long id) {
        Inscricao inscricaoExistente = buscarPorId(id);
        Repository.delete(inscricaoExistente);
    }

}