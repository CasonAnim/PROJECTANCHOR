public interface RemoteSave {
    void onRemote(int Channel, Object data);
    // 0 - For Request SaveDate
    // 1 - Receive Data
    // 2 - Sent Game Result For next Stage
    // 3 - Switch Panel
    // 3.1 - 1 - Back to Menu / Reset EndScreen
    // 3.2 - 2 - Back to Menu / ReEnabled UIMAINFRAME
    // 4 - Added Reward to playerDeck
    // 5 - Send Load up LoadSave menu
    // 6 - Load up progress
    // 7 - Thank you for playing

}
