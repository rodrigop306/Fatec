package View;
import Controller.Proc;
import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Proc p = new Proc();
		String os = p.osName();
		//System.out.println(os);
		int opc = 0;
		Scanner ler = new Scanner(System.in);
		while(opc!=3){
			System.out.println("1 - Para listar os processos \n" +
					"2 - Para matar um processo pelo nome ou ID \n" +
					"3 - Sair");
			opc = ler.nextInt();
			switch(opc){
			case 1:
				p.listProc(os);
				break;
			case 2:
				System.out.println("Digite o nome ou ID do processo");
				String kill = ler.next();
				p.mataProcesso(kill, os);
				break;
			case 3:
				System.exit(0);
				break;
			default:
				System.out.println("Digite uma opção válida");
				break;
			}
		}
	}

}
