/*function addLink() {
    var urlInput = document.getElementById('urlInput');
    var url = urlInput.value.trim();  // Lấy giá trị link nhập vào và loại bỏ khoảng trắng thừa
    
    if (url) {
        // Tạo một phần tử li mới
        var li = document.createElement('li');
        li.classList.add('link-item');
        
        // Tạo văn bản chứa liên kết
        var link = document.createElement('a');
        link.href = url;
        link.textContent = url;
        link.target = "_blank"; // Mở liên kết trong tab mới

        // Tạo nút xóa
        var deleteButton = document.createElement('button');
        deleteButton.textContent = "Xóa";
        deleteButton.onclick = function() {
            li.remove(); // Xoá phần tử li
        };

        // Thêm link và nút xóa vào li
        li.appendChild(link);
        li.appendChild(deleteButton);

        // Thêm li vào danh sách
        document.getElementById('linkList').appendChild(li);

        // Xoá nội dung trong input sau khi thêm
        urlInput.value = '';
    } else {
        alert("Vui lòng nhập một link hợp lệ!");
    }
} */