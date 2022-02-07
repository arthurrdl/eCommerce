
package Modelo;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Upload {
    private static String caminhoAtual = "C:/img/";
    private static String destino="/D:/eCommerce/web/imagens/";

    public static String getCaminhoAtual() {
        return caminhoAtual;
    }

    public static String getDestino() {
        return destino;
    }
    
    
    /*
     * Faz redimensionamento da imagem conforme os parâmetros imgLargura e imgAltura mantendo a proporcionalidade. 
     * Caso a imagem seja menor do que os parâmetros de redimensionamento, a imagem não será redimensionada. 
     *  
     * @param caminhoImg caminho e nome da imagem a ser redimensionada. 
     * @param imgLargura nova largura da imagem após ter sido redimensionada. 
     * @param imgAltura  nova altura da imagem após ter sido redimensionada. 
     *  
     * @return Não há retorno 
     * @throws Exception Erro ao redimensionar imagem 
     ************************************************************************************************************/
    public static BufferedImage setImagemDimensao(String caminhoImg) {
        Double novaImgLargura = null;
        Double novaImgAltura = null;
        Double imgProporcao = null;
        Graphics2D g2d = null;
        BufferedImage imagem = null, novaImagem = null;
        int imgLargura = 300 ; 
        int imgAltura = 300 ; 
        try {
            //--- Obtém a imagem a ser redimensionada ---
            imagem = ImageIO.read(new File(caminhoImg));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Upload.class.getName()).log(Level.SEVERE, null, ex);
        }

        //--- Obtém a largura da imagem ---  
        novaImgLargura = (double) imagem.getWidth();

        //--- Obtám a altura da imagem ---  
        novaImgAltura = (double) imagem.getHeight();

    //--- Verifica se a altura ou largura da imagem recebida é maior do que os ---  
        //--- parâmetros de altura e largura recebidos para o redimensionamento   ---  
        if (novaImgLargura >= imgLargura) {
            imgProporcao = (novaImgAltura / novaImgLargura);//calcula a proporção  
            novaImgLargura = (double) imgLargura;

            //--- altura deve <= ao parâmetro imgAltura e proporcional a largura ---  
            novaImgAltura = (novaImgLargura * imgProporcao);

        //--- se altura for maior do que o parâmetro imgAltura, diminui-se a largura de ---  
            //--- forma que a altura seja igual ao parâmetro imgAltura e proporcional a largura ---  
            while (novaImgAltura > imgAltura) {
                novaImgLargura = (double) (--imgLargura);
                novaImgAltura = (novaImgLargura * imgProporcao);
            }
        } else if (novaImgAltura >= imgAltura) {
            imgProporcao = (novaImgLargura / novaImgAltura);//calcula a proporção  
            novaImgAltura = (double) imgAltura;

        //--- se largura for maior do que o parâmetro imgLargura, diminui-se a altura de ---  
            //--- forma que a largura seja igual ao parâmetro imglargura e proporcional a altura ---  
            while (novaImgLargura > imgLargura) {
                novaImgAltura = (double) (--imgAltura);
                novaImgLargura = (novaImgAltura * imgProporcao);
            }
        }

        novaImagem = new BufferedImage(novaImgLargura.intValue(), novaImgAltura.intValue(), BufferedImage.TYPE_INT_RGB);
        g2d = novaImagem.createGraphics();
        g2d.drawImage(imagem, 0, 0, novaImgLargura.intValue(), novaImgAltura.intValue(), null);

        return novaImagem;
    }

    public static byte[] getImgBytes(BufferedImage image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "JPEG", baos);
        } catch (IOException ex) {
            //handle it here.... not implemented yet...
        }
        
        InputStream is = new ByteArrayInputStream(baos.toByteArray());
        
        return baos.toByteArray();
    }
    
    public static void tratarImagem(File arquivo , String nomeFoto) throws IOException{
        BufferedImage imagem;        
        imagem = setImagemDimensao(arquivo.getAbsolutePath());        
        String caminho = destino;     
        File outputfile = new File(caminho+nomeFoto);
        ImageIO.write(imagem, "jpg", outputfile);
        
       
    
    }
    
    
    
    
}
