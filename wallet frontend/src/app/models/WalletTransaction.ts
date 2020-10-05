export interface WalletTransaction{
    transactionId : number;
    description: string;
    dateOfTransaction: Date;
    amount: number;
    receiverAccountId: number;
    accountBalance: number;
}