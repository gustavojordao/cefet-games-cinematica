package br.cefetmg.games.movement.behavior;

import br.cefetmg.games.movement.AlgoritmoMovimentacao;
import br.cefetmg.games.movement.Direcionamento;
import br.cefetmg.games.movement.Pose;
import br.cefetmg.games.physics.Colisoes;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector3;

/**
 * Guia o agente na direção do alvo.
 *
 * @author Flávio Coutinho <fegemo@cefetmg.br>
 */
public class Chegar extends AlgoritmoMovimentacao {

    private static final char NOME = 'a';

    private float raio;
    
    public Chegar(float maxVelocidade, float raio) {
        this(NOME, maxVelocidade, raio);
    }

    protected Chegar(char nome, float maxVelocidade, float raio) {
        super(nome);
        this.raio = raio;
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
        Vector3 aux_sub = aux_objetivo.sub(aux_agente).cpy();
        Vector3 aux_nor = aux_sub.nor().cpy();
        Vector3 aux_scl = aux_nor.scl(maxVelocidade).cpy();
        
        boolean colidiu = Colisoes.colideCom(new Circle(aux_objetivo.x, aux_objetivo.y, raio), aux_agente);
        output.velocidade = colidiu ? Vector3.Zero : aux_scl;
        
        agente.olharNaDirecaoDaVelocidade(output.velocidade);
        
        return output;
                
    }

    @Override
    public int getTeclaParaAtivacao() {
        return Keys.A;
    }
}
