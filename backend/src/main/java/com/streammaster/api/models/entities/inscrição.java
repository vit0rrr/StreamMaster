@Entity
@Table(name = "inscricoes")
@Data // Anotação do Lombok que cria os getters e setters automaticamente
public class Inscricao {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String email;
    private LocalDateTime dataInscricao;
}