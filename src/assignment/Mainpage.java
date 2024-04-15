/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.io.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Im_tHe_rEaL_LiM
 */
    
public class Mainpage extends javax.swing.JFrame{

    public void transfer(String transfer,String receive,double amount) throws IOException{
        File account=new File("account.txt");
        File temp=new File("temp.txt");
        account.createNewFile();
        temp.createNewFile();
        
        FileWriter fw=new FileWriter(temp,true);
        PrintWriter pw=new PrintWriter(fw);
        int transfered=0;
        
        Scanner input=new Scanner(account);
        while(input.hasNext()){
            String record=input.nextLine();
            String[] details=record.split(",");
            if(details[0].equals(transfer)){
                double total=Double.parseDouble(details[3])-amount;
                if(details[2].equals("Savings")){
                    if(total<100){
                        JOptionPane.showMessageDialog(null, "Balance Not Enough","Withdrawal",JOptionPane.WARNING_MESSAGE);
                        txtacc_no.setText("");
                        txtamount1.setText("");
                        txtAccount_type.setText("");
                        txtBalance.setText("");
                        pw.println(details[0]+","+details[1]+","+details[2]+","+details[3]);
                    }else{
                        pw.println(details[0]+","+details[1]+","+details[2]+","+total);
                        txtacc_no.setText("");
                        txtamount1.setText("");
                        txtAccount_type.setText("");
                        txtBalance.setText("");
                        JOptionPane.showMessageDialog(null, "Transfer Successfull","Witthdrawal",JOptionPane.INFORMATION_MESSAGE);
                        transfered=1;
                    }
                }else if(details[2].equals("Current")){
                    if(total<500){
                        JOptionPane.showMessageDialog(null, "Balance Not Enough","Widthdrawal",JOptionPane.WARNING_MESSAGE);
                        txtacc_no.setText("");
                        txtamount1.setText("");
                        txtAccount_type.setText("");
                        txtBalance.setText("");
                        pw.println(details[0]+","+details[1]+","+details[2]+","+details[3]);
                    }else{
                        pw.println(details[0]+","+details[1]+","+details[2]+","+total);
                        txtacc_no.setText("");
                        txtamount1.setText("");
                        txtAccount_type.setText("");
                        txtBalance.setText("");
                        JOptionPane.showMessageDialog(null, "Transfer Successfull","Witthdrawal",JOptionPane.INFORMATION_MESSAGE);
                        transfered=1;
                    }
                }
            }else{
                pw.println(details[0]+","+details[1]+","+details[2]+","+details[3]);
            }
        }
        input.close();
        pw.close();
        fw.close();
        
        
        
        if(transfered==1){
            File temp1=new File("temp1.txt");
            temp1.createNewFile();
            FileWriter fw1=new FileWriter(temp1,true);
            PrintWriter pw1=new PrintWriter(fw1);
            
            Scanner input1 =new Scanner(temp);
            while(input1.hasNext()){
                String record1=input1.nextLine();
                String[] details1=record1.split(",");
                
                if(details1[0].equals(receive)){
                    double total=amount+Double.parseDouble(details1[3]);
                    pw1.println(details1[0]+","+details1[1]+","+details1[2]+","+total);
                }else{
                    pw1.println(details1[0]+","+details1[1]+","+details1[2]+","+details1[3]);  
                }
            }
            fw1.close();
            pw1.close();
            input1.close();
            temp.delete();
            account.delete();
            File dump=new File("account.txt");
            temp1.renameTo(dump);
        }else{
            account.delete();
            File dump=new File("account.txt");
            temp.renameTo(dump);
        }
        diaTransfer.dispose();
        
    }
    public void clock(){
        Thread clock=new Thread(){
            public void run(){
                try{
                    while(true){    
                    ZoneId m=ZoneId.of("Asia/Shanghai");
                    ZonedDateTime datetime1 = ZonedDateTime.now(m);
                    DateTimeFormatter datetimeformat = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
                    String formatteddate=datetime1.format(datetimeformat);
                    lbldatetime.setText(formatteddate);
                    
                    sleep(1000);
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        clock.start();
    }
    public void create_account(String name,String email,String phone_num,String account_type,double amount)throws IOException{
        File customer = new File("customer.txt");
        File account = new File("account.txt");
        int cus_id =0 ;
        int acc_id =0;
        
        customer.createNewFile();
        account.createNewFile();
        
        Scanner input=new Scanner(customer);
        while(input.hasNext()){
            String record = input.nextLine();
            String[] details = record.split(",");
             
            if(!input.hasNext()){
                cus_id=Integer.parseInt(details[0]);
            }
        }
        
        Scanner input1=new Scanner(account);
        while(input1.hasNext()){
            String record = input1.nextLine();
            String[] details1 = record.split(",");
             
            if(!input1.hasNext()){
                acc_id=Integer.parseInt(details1[0]);
            }
        }
        input.close();
        input1.close();
        
        FileWriter fw = new FileWriter(customer,true);
        PrintWriter pw= new PrintWriter(fw);
        FileWriter fw1 = new FileWriter(account,true);
        PrintWriter pw1= new PrintWriter(fw1);
        
        cus_id++;
        acc_id++;
        
        pw.println(cus_id+","+name+","+email+","+phone_num);
        pw1.println(acc_id+","+cus_id+","+account_type+","+amount);
        
        JOptionPane.showMessageDialog(null,"Your account number is "+ acc_id,"Account Created", JOptionPane.INFORMATION_MESSAGE);
        txtName.setText("");
        txtEmail.setText("");
        txtPhone_num.setText("");
        txtdeposit_amount.setText("");
        fw.close();
        fw1.close();
        pw.close();
        pw1.close();
        crt_a.dispose(); 
    }
   
    public void create_account1(int cus_id,String account_type,double amount)throws IOException{
        File account = new File("account.txt");
        int acc_id =0;
        
        Scanner input=new Scanner(account);
        while(input.hasNext()){
            String record=input.nextLine();
            String[] details=record.split(",");
            
            if(!input.hasNext()){
                acc_id=Integer.parseInt(details[0]);
            }
        }
        input.close();
        
        FileWriter fw=new FileWriter(account,true);
        PrintWriter pw=new PrintWriter(fw);
        
        acc_id++;
        
        pw.println(acc_id+","+cus_id+","+account_type+","+amount);
        JOptionPane.showMessageDialog(null,"Your account number is "+ acc_id,"Account Created", JOptionPane.INFORMATION_MESSAGE);
        txtEmail_customer.setText("");
        txtPhone_number.setText("");
        txtdeposit_amount1.setText("");
        fw.close();
        pw.close();
        crt_a1.dispose(); 
    }
    public void search_cus(String name) throws IOException{
        txtEmail_customer.setText("");
        txtPhone_number.setText("");
        customer_id.setText("");
        btnUpdate1.setEnabled(false);
        btnCreate_acc.setEnabled(false);
        
        File customer = new File("customer.txt");
       
        Scanner input=new Scanner(customer);
        while(input.hasNext()){
            String record=input.nextLine();
            String[] details=record.split(",");
            
            if (details[1].equals(name)){
                customer_id.setText(details[0]);
                txtEmail_customer.setText(details[2]);
                txtPhone_number.setText(details[3]);
                txtEmail_customer.setEnabled(true);
                txtPhone_number.setEnabled(true);
                btnUpdate1.setEnabled(true);
                btnCreate_acc.setEnabled(true);
            }else if(input.hasNext()==false && "".equals(txtEmail_customer.getText())){
                JOptionPane.showMessageDialog(null,"Person doesn't exist", "Search Failed",JOptionPane.WARNING_MESSAGE);
                txtCustomer_Name.setText("");
            }
        }
        input.close();
    }
    public void search_acc(String name) throws IOException{
        cbAccount_num.setEnabled(false);
        cbAccount_num.removeAllItems();

        File customer = new File("customer.txt");
        File account=new File("account.txt");
        int cus_id = 0;
        
        Scanner input=new Scanner(customer);
        while(input.hasNext()){
            String record=input.nextLine();
            String[] details=record.split(",");
            
            if (details[1].equals(name)){
                cus_id=Integer.parseInt(details[0]);
            }else if(input.hasNext()==false && cus_id<1){
                JOptionPane.showMessageDialog(null,"Person doesn't exist", "Search Failed",JOptionPane.WARNING_MESSAGE);
                txtCustomer_name.setText("");
            }
        }
        
        
        Scanner input1=new Scanner(account);
        
        while(input1.hasNext()){
            
            String record1=input1.nextLine();

            String[] details1=record1.split(",");
            if(details1[1].equals(Integer.toString(cus_id)) ){
                
                cbAccount_num.addItem(details1[0]);
                cbAccount_num.setEnabled(true);
            }
        }
        input.close();
        input1.close();
    }
    public void update() throws IOException{
        File cus=new File("customer.txt");
        File temp=new File("temp.txt");
        cus.createNewFile();
        temp.createNewFile();
        FileWriter fw= new FileWriter(temp,true);
        PrintWriter pw=new PrintWriter(fw);
        
        Scanner input=new Scanner(cus);
        while(input.hasNext()){
            String record=input.nextLine();
            String[] details=record.split(",");
            
            if(details[0].equals(customer_id.getText())){
                pw.println(details[0]+","+details[1]+","+txtEmail_customer.getText()+","+txtPhone_number.getText());
                JOptionPane.showMessageDialog(null,"Update Successful","Update",JOptionPane.INFORMATION_MESSAGE);
                txtEmail_customer.setText("");
                txtPhone_number.setText("");
            }else{
                pw.println(details[0]+","+details[1]+","+details[2]+","+details[3]);
            }
        }
        fw.close();
        pw.close();
        input.close();
        cus.delete();
        File dump=new File("customer.txt");
        temp.renameTo(dump);
    }
    private void find_acc() throws IOException{
        File account=new File("account.txt");
        Scanner input=new Scanner(account);
        while (input.hasNext()){
            String record=input.nextLine();
            String[] details=record.split(",");

            if(details[0].equals(cbAccount_num.getSelectedItem())){
                txtAccount_type.setText(details[2]);
                double balance=Double.parseDouble(details[3]);
                
                txtBalance.setText(String.format("%.2f", balance));
            }
        }
        input.close();
    }
    public void deposit(String acc_no,double amount) throws IOException{
        File account=new File("account.txt");
        File temp=new File("temp.txt");
        account.createNewFile();
        temp.createNewFile();
        
        FileWriter fw=new FileWriter(temp,true);
        PrintWriter pw=new PrintWriter(fw);
        
        Scanner input=new Scanner(account);
        while(input.hasNext()){
            String record=input.nextLine();
            String[] details=record.split(",");

            if(details[0].equals(acc_no)){            
                double total= amount+Double.parseDouble(details[3]);
                pw.println(details[0]+","+details[1]+","+details[2]+","+total);
                JOptionPane.showMessageDialog(null, "Deposit Successfully","Deposit",JOptionPane.INFORMATION_MESSAGE);
            }else{
                pw.println(details[0]+","+details[1]+","+details[2]+","+details[3]);
            }
        }
        txtAccount_Number.setText("");
        txtamount.setText("");
        fw.close();
        pw.close();
        input.close();
        account.delete();
        File dump=new File("account.txt");
        temp.renameTo(dump);
    }
    public void withdrawal(String acc_no,double amount) throws IOException{
        File account=new File("account.txt");
        File temp=new File("temp.txt");
        account.createNewFile();
        temp.createNewFile();
        
        FileWriter fw=new FileWriter(temp,true);
        PrintWriter pw=new PrintWriter(fw);
        
        Scanner input=new Scanner(account);
        while(input.hasNext()){
            String record=input.nextLine();
            String[] details=record.split(",");

            if(details[0].equals(acc_no)){            
                System.out.println("Hello");
                double total= Double.parseDouble(details[3])-amount;
                if(details[2].equals("Savings")){
                    if(total<100){
                        JOptionPane.showMessageDialog(null, "Balance Not Enough","Withdrawal",JOptionPane.WARNING_MESSAGE);
                        pw.println(details[0]+","+details[1]+","+details[2]+","+details[3]);
                        txtAccount_num.setText("");
                        txtAmount.setText("");
                    }else{
                        pw.println(details[0]+","+details[1]+","+details[2]+","+total);
                        txtAccount_num.setText("");
                        txtAmount.setText("");
                        JOptionPane.showMessageDialog(null, "Withdrawal Successfull","Witthdrawal",JOptionPane.INFORMATION_MESSAGE);
                    }
                }else if(details[2].equals("Current")){
                    if(total<500){
                        JOptionPane.showMessageDialog(null, "Balance Not Enough","Widthdrawal",JOptionPane.WARNING_MESSAGE);
                        pw.println(details[0]+","+details[1]+","+details[2]+","+details[3]);
                        txtAccount_num.setText("");
                        txtAmount.setText("");
                    }else{
                        pw.println(details[0]+","+details[1]+","+details[2]+","+total);
                        txtAccount_num.setText("");
                        txtAmount.setText("");
                        JOptionPane.showMessageDialog(null, "Withdrawal Successfull","Witthdrawal",JOptionPane.INFORMATION_MESSAGE);
                    }
                }

            }else{
                pw.println(details[0]+","+details[1]+","+details[2]+","+details[3]);
            }
        }
        fw.close();
        pw.close();
        input.close();
        account.delete();
        File dump=new File("account.txt");
        temp.renameTo(dump);
    }

    public Mainpage(String name) {
        initComponents();
        clock();
        staffname.setText(name);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        crt_a = new javax.swing.JDialog();
        lbltype = new javax.swing.JLabel();
        tbtn_type = new javax.swing.JToggleButton();
        lbldeposit = new javax.swing.JLabel();
        txtdeposit_amount = new javax.swing.JTextField();
        btnCreate = new javax.swing.JButton();
        crt_a1 = new javax.swing.JDialog();
        lbltype1 = new javax.swing.JLabel();
        tbtn_type1 = new javax.swing.JToggleButton();
        lbldeposit1 = new javax.swing.JLabel();
        txtdeposit_amount1 = new javax.swing.JTextField();
        btnCreate1 = new javax.swing.JButton();
        diaTransfer = new javax.swing.JDialog();
        label = new javax.swing.JLabel();
        txtacc_no = new javax.swing.JTextField();
        lblamount = new javax.swing.JLabel();
        txtamount1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        lblStafname = new javax.swing.JLabel();
        staffname = new javax.swing.JLabel();
        lbldatetime = new javax.swing.JLabel();
        jTabbedPane = new javax.swing.JTabbedPane();
        pnl_register = new javax.swing.JPanel();
        lblPhone_num = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtPhone_num = new javax.swing.JTextField();
        btnregister = new javax.swing.JButton();
        lblName = new javax.swing.JLabel();
        pnl_deposit = new javax.swing.JPanel();
        lblAccountid = new javax.swing.JLabel();
        lblAmount = new javax.swing.JLabel();
        txtamount = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnDeposit = new javax.swing.JButton();
        txtAccount_Number = new javax.swing.JTextField();
        pnl_withdrawal = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtAmount = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtAccount_num = new javax.swing.JTextField();
        pnllCustomer = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        customer_id = new javax.swing.JLabel();
        txtEmail_customer = new javax.swing.JTextField();
        txtPhone_number = new javax.swing.JTextField();
        txtCustomer_Name = new javax.swing.JTextField();
        btnUpdate1 = new javax.swing.JButton();
        btnCreate_acc = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        pnl_account = new javax.swing.JPanel();
        lblBalance = new javax.swing.JLabel();
        lblCustomer_name = new javax.swing.JLabel();
        lblAccount_num = new javax.swing.JLabel();
        lblAccount_type = new javax.swing.JLabel();
        txtCustomer_name = new javax.swing.JTextField();
        cbAccount_num = new javax.swing.JComboBox<>();
        txtAccount_type = new javax.swing.JLabel();
        txtBalance = new javax.swing.JLabel();
        btnSearch1 = new javax.swing.JButton();
        btntransfer = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        btnlogout = new javax.swing.JButton();

        crt_a.setTitle("Create Account");
        crt_a.setBounds(new java.awt.Rectangle(0, 0, 240, 170));
        crt_a.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lbltype.setText("Account Type:");

        tbtn_type.setSelected(true);
        tbtn_type.setText("Savings");
        tbtn_type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbtn_typeActionPerformed(evt);
            }
        });

        lbldeposit.setText("Deposit Amount :");

        txtdeposit_amount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtdeposit_amountFocusLost(evt);
            }
        });
        txtdeposit_amount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdeposit_amountActionPerformed(evt);
            }
        });

        btnCreate.setText("Create Account");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout crt_aLayout = new javax.swing.GroupLayout(crt_a.getContentPane());
        crt_a.getContentPane().setLayout(crt_aLayout);
        crt_aLayout.setHorizontalGroup(
            crt_aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crt_aLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(crt_aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(crt_aLayout.createSequentialGroup()
                        .addGroup(crt_aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbltype)
                            .addComponent(lbldeposit))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(crt_aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tbtn_type, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                            .addComponent(txtdeposit_amount)))
                    .addComponent(btnCreate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        crt_aLayout.setVerticalGroup(
            crt_aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crt_aLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(crt_aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbltype)
                    .addComponent(tbtn_type))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(crt_aLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbldeposit)
                    .addComponent(txtdeposit_amount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnCreate)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        crt_a1.setTitle("Create Account");
        crt_a1.setBounds(new java.awt.Rectangle(0, 0, 240, 170));
        crt_a1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        crt_a1.setResizable(false);

        lbltype1.setText("Account Type:");

        tbtn_type1.setSelected(true);
        tbtn_type1.setText("Savings");
        tbtn_type1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbtn_type1ActionPerformed(evt);
            }
        });

        lbldeposit1.setText("Deposit Amount :");

        txtdeposit_amount1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtdeposit_amount1FocusLost(evt);
            }
        });
        txtdeposit_amount1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdeposit_amount1ActionPerformed(evt);
            }
        });

        btnCreate1.setText("Create Account");
        btnCreate1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreate1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout crt_a1Layout = new javax.swing.GroupLayout(crt_a1.getContentPane());
        crt_a1.getContentPane().setLayout(crt_a1Layout);
        crt_a1Layout.setHorizontalGroup(
            crt_a1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crt_a1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(crt_a1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(crt_a1Layout.createSequentialGroup()
                        .addGroup(crt_a1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbltype1)
                            .addComponent(lbldeposit1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(crt_a1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tbtn_type1, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                            .addComponent(txtdeposit_amount1)))
                    .addComponent(btnCreate1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        crt_a1Layout.setVerticalGroup(
            crt_a1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crt_a1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(crt_a1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbltype1)
                    .addComponent(tbtn_type1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(crt_a1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbldeposit1)
                    .addComponent(txtdeposit_amount1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnCreate1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        diaTransfer.setTitle("Transfer To");
        diaTransfer.setBounds(new java.awt.Rectangle(0, 0, 240, 170));
        diaTransfer.setPreferredSize(new java.awt.Dimension(218, 157));
        diaTransfer.setSize(new java.awt.Dimension(218, 157));

        label.setText("Account Number:");

        lblamount.setText("Amount:");

        jButton2.setText("Transfer");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout diaTransferLayout = new javax.swing.GroupLayout(diaTransfer.getContentPane());
        diaTransfer.getContentPane().setLayout(diaTransferLayout);
        diaTransferLayout.setHorizontalGroup(
            diaTransferLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(diaTransferLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(diaTransferLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(diaTransferLayout.createSequentialGroup()
                        .addComponent(label)
                        .addGap(4, 4, 4)
                        .addComponent(txtacc_no, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(diaTransferLayout.createSequentialGroup()
                        .addComponent(lblamount)
                        .addGap(47, 47, 47)
                        .addComponent(txtamount1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        diaTransferLayout.setVerticalGroup(
            diaTransferLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(diaTransferLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(diaTransferLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label)
                    .addComponent(txtacc_no, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(diaTransferLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(diaTransferLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblamount))
                    .addComponent(txtamount1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Main Page");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        lblStafname.setText("Staff :");

        lbldatetime.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        pnl_register.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 153, 153)), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));

        lblPhone_num.setText("Phone Number :");

        lblEmail.setText("Email :");

        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        btnregister.setText("Register");
        btnregister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregisterActionPerformed(evt);
            }
        });

        lblName.setText("Name :");

        javax.swing.GroupLayout pnl_registerLayout = new javax.swing.GroupLayout(pnl_register);
        pnl_register.setLayout(pnl_registerLayout);
        pnl_registerLayout.setHorizontalGroup(
            pnl_registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_registerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnregister, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnl_registerLayout.createSequentialGroup()
                        .addGroup(pnl_registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnl_registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblPhone_num, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
                            .addComponent(lblName, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnl_registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                            .addComponent(txtPhone_num, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtName))))
                .addGap(40, 40, 40))
        );
        pnl_registerLayout.setVerticalGroup(
            pnl_registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_registerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnl_registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnl_registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblPhone_num, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPhone_num, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnregister, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("Cutsomer Register", pnl_register);

        pnl_deposit.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 153, 153)), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));

        lblAccountid.setText("Account Number:");

        lblAmount.setText("Amount:");

        txtamount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtamountActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("RM");

        btnDeposit.setText("Deposit");
        btnDeposit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepositActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_depositLayout = new javax.swing.GroupLayout(pnl_deposit);
        pnl_deposit.setLayout(pnl_depositLayout);
        pnl_depositLayout.setHorizontalGroup(
            pnl_depositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_depositLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_depositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnl_depositLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnDeposit, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl_depositLayout.createSequentialGroup()
                        .addGroup(pnl_depositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblAmount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblAccountid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnl_depositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnl_depositLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtamount, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnl_depositLayout.createSequentialGroup()
                                .addComponent(txtAccount_Number, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(546, 546, 546))
        );
        pnl_depositLayout.setVerticalGroup(
            pnl_depositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_depositLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_depositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAccountid)
                    .addComponent(txtAccount_Number, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnl_depositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnl_depositLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblAmount)
                        .addComponent(txtamount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(btnDeposit, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(99, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("Deposit", pnl_deposit);

        pnl_withdrawal.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 153, 153)), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));

        jLabel1.setText("Account Number:");

        jLabel4.setText("Amount :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("RM");

        jButton1.setText("Withdrawal");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_withdrawalLayout = new javax.swing.GroupLayout(pnl_withdrawal);
        pnl_withdrawal.setLayout(pnl_withdrawalLayout);
        pnl_withdrawalLayout.setHorizontalGroup(
            pnl_withdrawalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_withdrawalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_withdrawalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_withdrawalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(pnl_withdrawalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtAccount_num, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(75, 75, 75))
        );
        pnl_withdrawalLayout.setVerticalGroup(
            pnl_withdrawalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_withdrawalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_withdrawalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtAccount_num, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnl_withdrawalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(96, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("Withdrawal", pnl_withdrawal);
        pnl_withdrawal.getAccessibleContext().setAccessibleName("");

        pnllCustomer.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 153, 153)), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));

        jLabel6.setText("Customer ID :");

        jLabel7.setText("Email :");

        jLabel8.setText("Phone Number :");

        jLabel9.setText("Customer Name :");

        txtEmail_customer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmail_customerActionPerformed(evt);
            }
        });

        btnUpdate1.setText("Update");
        btnUpdate1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdate1ActionPerformed(evt);
            }
        });

        btnCreate_acc.setText("Create New Account");
        btnCreate_acc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreate_accActionPerformed(evt);
            }
        });

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnllCustomerLayout = new javax.swing.GroupLayout(pnllCustomer);
        pnllCustomer.setLayout(pnllCustomerLayout);
        pnllCustomerLayout.setHorizontalGroup(
            pnllCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnllCustomerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnllCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(pnllCustomerLayout.createSequentialGroup()
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                        .addComponent(btnCreate_acc, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnllCustomerLayout.createSequentialGroup()
                        .addGroup(pnllCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(pnllCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtEmail_customer)
                            .addComponent(txtPhone_number, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                            .addComponent(customer_id, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCustomer_Name))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        pnllCustomerLayout.setVerticalGroup(
            pnllCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnllCustomerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnllCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(customer_id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnllCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCustomer_Name, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addGap(15, 15, 15)
                .addGroup(pnllCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail_customer, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(pnllCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPhone_number, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnllCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnllCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCreate_acc, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnUpdate1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14))
        );

        jTabbedPane.addTab("Customer", pnllCustomer);

        pnl_account.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 153, 153)), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));

        lblBalance.setText("Balance:");

        lblCustomer_name.setText("Customer Name:");

        lblAccount_num.setText("Account Number:");

        lblAccount_type.setText("Account Type:");

        txtCustomer_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCustomer_nameActionPerformed(evt);
            }
        });

        cbAccount_num.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbAccount_num.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAccount_numActionPerformed(evt);
            }
        });

        btnSearch1.setText("Search");
        btnSearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearch1ActionPerformed(evt);
            }
        });

        btntransfer.setText("Transfer");
        btntransfer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntransferActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("RM");

        javax.swing.GroupLayout pnl_accountLayout = new javax.swing.GroupLayout(pnl_account);
        pnl_account.setLayout(pnl_accountLayout);
        pnl_accountLayout.setHorizontalGroup(
            pnl_accountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_accountLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_accountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btntransfer, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnl_accountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblCustomer_name)
                        .addComponent(lblAccount_num, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblAccount_type, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblBalance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_accountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_accountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtCustomer_name)
                        .addComponent(cbAccount_num, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_accountLayout.createSequentialGroup()
                            .addGap(263, 263, 263)
                            .addComponent(btnSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtAccount_type, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_accountLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
        );
        pnl_accountLayout.setVerticalGroup(
            pnl_accountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_accountLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pnl_accountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnl_accountLayout.createSequentialGroup()
                        .addComponent(cbAccount_num, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88)
                        .addComponent(btnSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl_accountLayout.createSequentialGroup()
                        .addGroup(pnl_accountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCustomer_name, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCustomer_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(lblAccount_num, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(pnl_accountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAccount_type, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAccount_type, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnl_accountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btntransfer, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("Account", pnl_account);

        btnlogout.setText("Logout");
        btnlogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnlogout, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(lblStafname, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(staffname, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbldatetime, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblStafname, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(staffname, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbldatetime, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnlogout)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void btnregisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregisterActionPerformed
        
        
        if(txtName.getText().isEmpty()| txtEmail.getText().isEmpty()|txtPhone_num.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Please fill up all the field","Fields not fill up.",JOptionPane.ERROR_MESSAGE);
        }else if(!txtName.getText().matches("[a-zA-Z\\s']+")|txtPhone_num.getText().matches("^[a-zA-Z]*$")){
            JOptionPane.showMessageDialog(null,"Please enter data in the correct format","Invalid Data",JOptionPane.WARNING_MESSAGE);
        }else{
            crt_a.show();
        }

    }//GEN-LAST:event_btnregisterActionPerformed

    private void txtamountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtamountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtamountActionPerformed

    private void btnDepositActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDepositActionPerformed
        if(txtAccount_Number.getText().isEmpty()| txtamount.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Please fill up all the field","Fields not fill up.",JOptionPane.ERROR_MESSAGE);
        }else if(txtamount.getText().matches("^[a-zA-Z]*$")|txtAccount_Number.getText().matches("^[a-zA-Z]*$")){
            JOptionPane.showMessageDialog(null,"Please enter data in the correct format","Invalid Data",JOptionPane.WARNING_MESSAGE);
        }else{
            try {
                deposit(txtAccount_Number.getText(),Double.parseDouble(txtamount.getText()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnDepositActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(txtAccount_num.getText().isEmpty()| txtAmount.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Please fill up all the field","Fields not fill up.",JOptionPane.ERROR_MESSAGE);
        }else if(txtAmount.getText().matches("^[a-zA-Z]*$")|txtAccount_num.getText().matches("^[a-zA-Z]*$")){
            JOptionPane.showMessageDialog(null,"Please enter data in the correct format","Invalid Data",JOptionPane.WARNING_MESSAGE);
        }else{
            try {
                withdrawal(txtAccount_num.getText(),Double.parseDouble(txtAmount.getText()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtCustomer_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCustomer_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCustomer_nameActionPerformed

    private void txtEmail_customerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmail_customerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmail_customerActionPerformed

    private void btnlogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlogoutActionPerformed
        this.setVisible(false);
        Login login=new Login();
        login.setVisible(true);
    }//GEN-LAST:event_btnlogoutActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        this.setLocationRelativeTo(null);
        crt_a.setLocationRelativeTo(null);
        crt_a1.setLocationRelativeTo(null);
        diaTransfer.setLocationRelativeTo(null);
        cbAccount_num.setEnabled(false);
        cbAccount_num.removeAllItems();
        btnUpdate1.setEnabled(false);
        btnCreate_acc.setEnabled(false);
        btntransfer.setEnabled(false);
        txtEmail_customer.setEnabled(false);
        txtPhone_number.setEnabled(false);

        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowActivated

    private void btnUpdate1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdate1ActionPerformed
        
        
        if(txtCustomer_Name.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Please fill up the Customer Name field","Fields not fill up.",JOptionPane.ERROR_MESSAGE);
        }else if(txtPhone_number.getText().matches("^[a-zA-Z]*$")){
            JOptionPane.showMessageDialog(null,"Please enter data in the correct format","Invalid Data",JOptionPane.WARNING_MESSAGE);
        }else{
            try {
                update();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnUpdate1ActionPerformed

    private void btnCreate_accActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreate_accActionPerformed
        crt_a1.show();
    }//GEN-LAST:event_btnCreate_accActionPerformed

    private void tbtn_typeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbtn_typeActionPerformed
        if(tbtn_type.isSelected()){
            tbtn_type.setText("Savings");
        }else{
            tbtn_type.setText("Current");
        };

    }//GEN-LAST:event_tbtn_typeActionPerformed

    private void txtdeposit_amountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtdeposit_amountFocusLost
        
    }//GEN-LAST:event_txtdeposit_amountFocusLost

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        String account_type=tbtn_type.getText();
        Double amount=Double.parseDouble(txtdeposit_amount.getText());
        if(tbtn_type.getText()=="Savings"){
            if(amount<100){
              txtdeposit_amount.setText("");
              JOptionPane.showMessageDialog(null, "Minimum Balance for Savings account must be RM100","Minimum Balance",JOptionPane.ERROR_MESSAGE);
            }else{
                try {
                    create_account(txtName.getText(),txtEmail.getText(),txtPhone_num.getText(),tbtn_type.getText(),amount);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }else if(tbtn_type.getText()=="Current"){
            if(amount<500){
              txtdeposit_amount.setText("");
              JOptionPane.showMessageDialog(null, "Minimum Balance for Current account must be RM500","Minimum Balance",JOptionPane.ERROR_MESSAGE);
            }else{
                try {
                    create_account(txtName.getText(),txtEmail.getText(),txtPhone_num.getText(),tbtn_type.getText(),amount);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_btnCreateActionPerformed

    private void txtdeposit_amountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdeposit_amountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdeposit_amountActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        if(txtCustomer_Name.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Please fill up all the field","Fields not fill up.",JOptionPane.ERROR_MESSAGE);
        }else if(!txtCustomer_Name.getText().matches("[a-zA-Z\\s']+")){
            JOptionPane.showMessageDialog(null,"Please enter data in the correct format","Invalid Data",JOptionPane.WARNING_MESSAGE);
        }else{
            try {
                search_cus(txtCustomer_Name.getText());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void tbtn_type1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbtn_type1ActionPerformed
        if(tbtn_type1.isSelected()){
            tbtn_type1.setText("Savings");
        }else{
            tbtn_type1.setText("Current");
        };
    }//GEN-LAST:event_tbtn_type1ActionPerformed

    private void txtdeposit_amount1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtdeposit_amount1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdeposit_amount1FocusLost

    private void txtdeposit_amount1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdeposit_amount1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdeposit_amount1ActionPerformed

    private void btnCreate1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreate1ActionPerformed
        String account_type=tbtn_type1.getText();
        Double amount1=Double.parseDouble(txtdeposit_amount1.getText());
        if(tbtn_type1.getText()=="Savings"){
            if(amount1<100){
              txtdeposit_amount1.setText("");
              JOptionPane.showMessageDialog(null, "Minimum Balance for Savings account must be RM100","Minimum Balance",JOptionPane.ERROR_MESSAGE);
            }else{
                try {
                    create_account1(Integer.parseInt(customer_id.getText()),tbtn_type1.getText(),amount1);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }else if(tbtn_type1.getText()=="Current"){
            if(amount1<500){
              txtdeposit_amount1.setText("");
              JOptionPane.showMessageDialog(null, "Minimum Balance for Current account must be RM500","Minimum Balance",JOptionPane.ERROR_MESSAGE);
            }else{
                try {
                    create_account1(Integer.parseInt(customer_id.getText()),tbtn_type1.getText(),amount1);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    
    }//GEN-LAST:event_btnCreate1ActionPerformed

    private void btnSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearch1ActionPerformed
        if(txtCustomer_name.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Please fill up all the field","Fields not fill up.",JOptionPane.ERROR_MESSAGE);
        }else if(!txtCustomer_name.getText().matches("[a-zA-Z\\s']+")){
            JOptionPane.showMessageDialog(null,"Please enter data in the correct format","Invalid Data",JOptionPane.WARNING_MESSAGE);
        }else{
            try {
                search_acc(txtCustomer_name.getText());
                btntransfer.setEnabled(true);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnSearch1ActionPerformed

    private void cbAccount_numActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAccount_numActionPerformed
        if(cbAccount_num.getSelectedIndex()>-1){
            try {
                find_acc();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_cbAccount_numActionPerformed

    private void btntransferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntransferActionPerformed
        diaTransfer.show();
    }//GEN-LAST:event_btntransferActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            transfer(cbAccount_num.getSelectedItem().toString(),txtacc_no.getText(),Double.parseDouble(txtamount1.getText()));
            System.out.println(cbAccount_num.getSelectedItem());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */


        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Mainpage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mainpage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mainpage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mainpage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnCreate1;
    private javax.swing.JButton btnCreate_acc;
    private javax.swing.JButton btnDeposit;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSearch1;
    private javax.swing.JButton btnUpdate1;
    private javax.swing.JButton btnlogout;
    private javax.swing.JButton btnregister;
    private javax.swing.JButton btntransfer;
    private javax.swing.JComboBox<String> cbAccount_num;
    private javax.swing.JDialog crt_a;
    private javax.swing.JDialog crt_a1;
    private javax.swing.JLabel customer_id;
    private javax.swing.JDialog diaTransfer;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTabbedPane jTabbedPane;
    private javax.swing.JLabel label;
    private javax.swing.JLabel lblAccount_num;
    private javax.swing.JLabel lblAccount_type;
    private javax.swing.JLabel lblAccountid;
    private javax.swing.JLabel lblAmount;
    private javax.swing.JLabel lblBalance;
    private javax.swing.JLabel lblCustomer_name;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPhone_num;
    private javax.swing.JLabel lblStafname;
    private javax.swing.JLabel lblamount;
    private javax.swing.JLabel lbldatetime;
    private javax.swing.JLabel lbldeposit;
    private javax.swing.JLabel lbldeposit1;
    private javax.swing.JLabel lbltype;
    private javax.swing.JLabel lbltype1;
    private javax.swing.JPanel pnl_account;
    private javax.swing.JPanel pnl_deposit;
    private javax.swing.JPanel pnl_register;
    private javax.swing.JPanel pnl_withdrawal;
    private javax.swing.JPanel pnllCustomer;
    public javax.swing.JLabel staffname;
    private javax.swing.JToggleButton tbtn_type;
    private javax.swing.JToggleButton tbtn_type1;
    private javax.swing.JTextField txtAccount_Number;
    private javax.swing.JTextField txtAccount_num;
    private javax.swing.JLabel txtAccount_type;
    private javax.swing.JTextField txtAmount;
    private javax.swing.JLabel txtBalance;
    private javax.swing.JTextField txtCustomer_Name;
    private javax.swing.JTextField txtCustomer_name;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEmail_customer;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPhone_num;
    private javax.swing.JTextField txtPhone_number;
    private javax.swing.JTextField txtacc_no;
    private javax.swing.JTextField txtamount;
    private javax.swing.JTextField txtamount1;
    private javax.swing.JTextField txtdeposit_amount;
    private javax.swing.JTextField txtdeposit_amount1;
    // End of variables declaration//GEN-END:variables
}
