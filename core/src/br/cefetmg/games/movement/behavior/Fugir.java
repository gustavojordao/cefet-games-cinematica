package br.cefetmg.games.movement.behavior;

import br.cefetmg.games.movement.AlgoritmoMovimentacao;
import br.cefetmg.games.movement.Direcionamento;
import br.cefetmg.games.movement.Pose;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector3;

/**
 * Guia o agente de forma a fugir na direção contrária ao alvo.
 *
 * @author Flávio Coutinho <fegemo@cefetmg.br>
 */
public class Fugir extends AlgoritmoMovimentacao {

    private static final char NOME = 'f';

    public Fugir(float maxVelocidade) {
        this(NOME, maxVelocidade);
    }
    
    protected Fugir(char nome, float maxVelocidade) {
        super(nome);
        this.maxVelocidade = maxVelocidade;
    }
    
    @Override
    public Direcionamento guiar(Pose agente) {
        Direcionamento output = new Direcionamento();

        // calcula que direção tomar (configura um objeto Direcionamento 
        // e o retorna)
        // ...
        // super.alvo já contém a posição do alvo
        // agente (parâmetro) é a pose do agente que estamos guiando
        // ...
       
        Vector3 aux_agente = agente.posicao.cpy();
        Vector3 aux_objetivo = super.alvo.getObjetivo().cpy();
        Vector3 aux_sub = aux_agente.sub(aux_objetivo);
        Vector3 aux_nor = aux_sub.nor();
        Vector3 aux_scl = aux_nor.scl(maxVelocidade);
        
        output.velocidade = aux_scl;
        
        agente.olharNaDirecaoDaVelocidade(output.velocidade);
        
        return output;
    }

    @Override
    public int getTeclaParaAtivacao() {
        return Keys.F;
    }

}
