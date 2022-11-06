/**
 * @author nan
 * @date 2022/10/24 16:07
 * @Description 设计 Goal 解析器
 */
public class interpret {

    public String interpret(String command) {
        StringBuilder stb = new StringBuilder();
        char[] chars = command.toCharArray();
        for(int i = 0; i < chars.length;i++){
            switch(chars[i]){
                case 'G':
                    stb.append("G");
                    break;
                case '(':
                    i++;
                    if(chars[i] == ')'){
                        stb.append("o");
                    }else{
                        stb.append("al");
                        i += 2;
                    }
                    break;
                default:break;
                    
            }
        }
        return stb.toString();
    }
}
