const chatButton = document.querySelector('.chatbox__button');
        const chatContent = document.querySelector('.chatbox__support');
        const icons = {
            isClicked: '<img src="chatbox/chatbox-icon2.svg" alt="">',
            isNotClicked: '<img src="chatbox/chatbox-icon.svg" alt="">'
        }
        const chatbox = new InteractiveChatbox(chatButton, chatContent, icons);
        chatbox.display();
        chatbox.toggleIcon(false, chatButton);