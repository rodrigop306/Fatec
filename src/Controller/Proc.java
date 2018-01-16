package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

public class Proc {
	public String osName() {
		String os = System.getProperty("os.name");
		return os;
	}
	public void listProc(String os){
		try {
			Process p=null;
			if(os.contains("Windows")) {			
				p = Runtime.getRuntime().exec("TASKLIST.EXE /FO TABLE");
			}else if(os.contains("Linux")){
				p = Runtime.getRuntime().exec("ps aux");
			}
			InputStream fluxo = p.getInputStream();
			InputStreamReader leFluxo = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leFluxo);
			String linha = buffer.readLine();
			while(linha!=null){
				System.out.println(linha);
				linha = buffer.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public void chamaProcesso(String caminhoProcesso){
		try {
			Runtime.getRuntime().exec(caminhoProcesso);
		} catch (IOException e) {
			//e.printStackTrace();
//			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", 
//					JOptionPane.ERROR_MESSAGE);
			String erro = e.getMessage();
			if(erro.contains("740")){
				StringBuffer buffer = new StringBuffer();
				buffer.append("cmd.exe /c ");
				buffer.append(caminhoProcesso);
				try {
					Runtime.getRuntime().exec(buffer.toString());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}else{
				JOptionPane.showMessageDialog(null, e.getMessage(), "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public void mataProcesso(String processo, String os){
		String cmdPid ="";
		String cmdNome=""; 
		if(os.contains("Windows")) {
			cmdPid = "TASKKILL /PID ";
		 	cmdNome = "TASKKILL /IM ";
		}else if(os.contains("Linux")){
			cmdPid = "kill ";
			cmdNome = "killall ";
		}
		int pid = 0;
		StringBuffer buffer = new StringBuffer();
		try{
			pid = Integer.parseInt(processo);
			buffer.append(cmdPid);
			buffer.append(pid);
		}catch(NumberFormatException e){
			buffer.append(cmdNome);
			buffer.append(processo);
		}
		chamaProcesso(buffer.toString());
	}
	
}
