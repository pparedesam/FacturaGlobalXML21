package wsHomologador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class comprimirXML {
	public static void comprimir(String ruta_zip, String ruta_arch, String nombre){
		try{
			FileOutputStream fos = new FileOutputStream(ruta_zip);
			ZipOutputStream zos = new ZipOutputStream(fos);
			agregar_a_zip(zos,ruta_arch,nombre);
			zos.close();
			fos.close();
			
		}catch(Exception e){}
	}

	private static void agregar_a_zip(ZipOutputStream zos, String ruta_arch, String nombre) throws Exception{
				File xml = new File(ruta_arch);
				FileInputStream fis = new FileInputStream(xml);
				ZipEntry entrada = new ZipEntry(nombre);
				zos.putNextEntry(entrada);
				byte[] bytes = new byte[4096];
				int tam;
				while((tam = fis.read(bytes))>=0){
					zos.write(bytes,0,tam);
				}
				
				zos.closeEntry();
				fis.close();
				
	}
}
