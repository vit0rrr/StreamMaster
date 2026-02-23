@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {
    // O Spring Boot cria a query de busca no banco sozinho só pelo nome do método!
    boolean existsByEmail(String email); 
}