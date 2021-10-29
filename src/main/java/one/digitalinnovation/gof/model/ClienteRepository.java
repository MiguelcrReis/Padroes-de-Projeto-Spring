package one.digitalinnovation.gof.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Os Repository's vão ser as interfaces que vão prover todos os metodos de acesso a dados daquela determinada Entity.
 *
 * CrudRepository = Strategy (uma interface que obriga a seguir determinada estrategia de implementação)
 * Só pelo fato de extender um cara de Repository, o Spring vai entender que eu quero implementar algo concreto
 * em relação a acesso e persistência de dados e já vai enjetar isso para mim se eu quiser (por conta de seus facilitadores)
 * assim não precisando nem colocar o "@Repository" no codigo, porem foi colocado apenas para deixar ciente de que esse elemento
 * é um Repository.
 *
 * @author MiguelCrReis
 */

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
}
