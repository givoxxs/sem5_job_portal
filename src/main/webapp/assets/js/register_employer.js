let links = (document.getElementById("link").value)? document.getElementById("link").value : "";

function addLink() {
    var urlInput = document.getElementById('urlInput');
    var url = urlInput.value.trim();  // Lấy giá trị link nhập vào và loại bỏ khoảng trắng thừa
    
    if (url) {
		// Thêm url mới vào links với dấu ; ngăn cách
        links += (links ? ";" : "") + url;
        
        document.getElementById("link").value = links;
        
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
        deleteButton.textContent = "Delete";
        deleteButton.onclick = function() {
            removeLink(url, li); // Gọi hàm xoá link và phần tử li
        };

        // Thêm link và nút xóa vào li
        li.appendChild(link);
        li.appendChild(deleteButton);

        // Thêm li vào danh sách
        document.getElementById('linkList').appendChild(li);

        // Xoá nội dung trong input sau khi thêm
        urlInput.value = '';
    } else {
        alert("Invalid link!");
    }
}

function removeLink(url, li) {
    // Cập nhật lại biến links bằng cách loại bỏ link đã xoá
    let linkArray = links.split(';');
    linkArray = linkArray.filter(link => link !== url);  // Loại bỏ link bị xóa
    links = linkArray.join(';');  // Nối lại các link thành chuỗi

    document.getElementById("link").value = links; // Cập nhật lại giá trị trong trường hidden

    // Xóa phần tử li khỏi danh sách
    li.remove();
}