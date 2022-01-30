package models;
import com.google.gson.JsonObject;
import org.json.simple.JSONObject;

public class Produto {
    public String nome;
    public String preco;
    public String descricao;
    public String _id;
    public String quantidade;

    public Produto(String nome,String preco,String descricao,String quantidade){
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.quantidade = quantidade;


    }
    public void setProdutoId(String userId){
        this._id = userId;
    }

    public String getProdutoQuantidade(){
        JsonObject userJsonRepresentation = new JsonObject();
        userJsonRepresentation.addProperty("nome",this.nome);
        userJsonRepresentation.addProperty("quantidade",this.quantidade);
        return userJsonRepresentation.toString();

    }


}
