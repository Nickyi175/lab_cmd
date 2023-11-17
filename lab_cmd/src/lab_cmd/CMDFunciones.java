package lab_cmd;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CMDFunciones {

    public File actDir;

    public CMDFunciones(String startPath) {
        actDir = new File(startPath);
    }

    public String CDBACK(String path) {
        if (path.charAt(0) != '/') {
            File newDir = new File(actDir.getAbsolutePath() + "/" + path);

            if (!newDir.isDirectory()) {
                return "Error. La direccion tiene que ser una carpeta.";
            }
            actDir = newDir;
            System.out.println("NUEVA DIRECCIÓN " + newDir.getAbsolutePath());
            return "";
        }

        actDir = new File(path);
        return "";
    }

    public String mkdir(String path) {
        String mensaje = "";
        File folder = new File(path);

        if (folder.exists()) {
            mensaje = "\nError: Carpeta existente\n";
            return mensaje;
        } else {

            mensaje = "\nCarpeta creada existosamente\n";
            folder.mkdir();
            return mensaje;
        }
    }

    public String Mfile(String path) {
        String mensaje = "";
        File archivo = new File(path);

        if (archivo.exists()) {
            mensaje = "\nError: Este archivo ya existe\n";
            return mensaje;
        } else {
            mensaje = "\nArchivo creado existosamente\n";

            try {
                archivo.createNewFile();
            } catch (IOException e) {
                mensaje = "\nError: no se pudo crear\n";
            }
            return mensaje;

        }
    }

    public void vaciar(File Vaciar) {
        if (Vaciar.isDirectory()) {
            for (File vacio : Vaciar.listFiles()) {
                vacio.delete();
            }
        }

    }

    public String eliminarCarpeta(File delet) {
        if (delet.isDirectory()) {
            for (File fil : delet.listFiles()) {
                if (fil.isDirectory()) {
                    vaciar(fil);
                    fil.delete();
                } else {
                    fil.delete();
                }
            }
            delet.delete();
            return "\nCarpeta Vaciada\n";
        }

        if (delet.isFile()) {
            delet.delete();
            return "\nArchivo eliminado\n";
        }

        return "\nError\n";
    }
    
    public static String listar(String path) {
        String lista = "";
        String titulo = "";
        File listado = new File(path);

        if (listado.isDirectory()) {

            for (File archivo : listado.listFiles()) {
                String tipo = "";
                if (archivo.isDirectory()) {
                    tipo = "<DIR>";
                } else if (archivo.isFile()) {
                    tipo = "     ";
                }
                double bytes = 0;
                if (archivo.isFile()) {
                    bytes = archivo.length();
                }
                String nombre = archivo.getName();
                String fechaMod = new Date(archivo.lastModified()).toString();
                titulo = "- FECHA DE MODIFICACIÓN     -   TIPO  - BYTES -        NOMBRE       \n";
                lista += fechaMod + "\t" + tipo + "\t" + bytes + "\t" + nombre + "\n";
                //lista += "\n ->" + archivo.getName();
            }

            return titulo + lista;
        } else {
            return "\nError: debe seleccionar un directorio\n";
        }
    }

    public String Time() {
        SimpleDateFormat tF = new SimpleDateFormat("HH:mm:ss");
        return "\nHORA ACTUAL: " + tF.format(new Date())+"\n";
    }

    public String fecha() {
        Date dt = new Date();
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        return "\nFECHA ACTUAL:\t" + df.format(dt)+"\n";
    }

    public String Escribir(String mensaje, String path) {
        File escrib = new File(path);
        String salida = "";

        if (escrib.exists()) {
            if (escrib.isFile()) {
                try {
                    FileWriter fr = new FileWriter(escrib);
                    fr.write(mensaje);
                    fr.flush();
                } catch (IOException e) {
                    salida = "\nError: no se pudo crear\n";
                }
                salida = "\nEscritura completada exitosamente\n";
                return salida;
            } else {
                salida = "\nError: Debe seleccionar un archivo para escribir\n";
                return salida;
            }
        } else {
            salida = "\nError: Archivo inexistente\n";
            return salida;
        }

    }

    public static String Leer(String path) {
        File read = new File(path);
        String mensaje = "";

        if (read.exists()) {
            if (read.isFile()) {
                try {
                    FileReader fr = new FileReader(read);

                    String contenido = "";

                    for (int i = fr.read(); i != -1; i = fr.read()) {
                        contenido += (char) i;
                    }
                    return contenido;

                } catch (IOException e) {
                    mensaje = "\nError en la lectura\n";
                    return mensaje;
                }
            } else {
                mensaje = "\nError: debe seleccionar un archivo\n";
                return mensaje;
            }
        } else {
            mensaje = "\nError: Archivo inexistente\n";
            return mensaje;
        }
    }

    public String getCurrentPath() {
        try {
            return actDir.getCanonicalPath();
        } catch (Exception e) {
            System.out.println("\nError\n");
            return actDir.getAbsolutePath();
        }

    }

    

}
