document.addEventListener("DOMContentLoaded", () => {
    const manageCandidatesTab = document.getElementById("manageCandidatesTab");
    const manageEmployersTab = document.getElementById("manageEmployersTab");
    const manageCandidates = document.getElementById("manageCandidates");
    const manageEmployers = document.getElementById("manageEmployers");

    if (manageCandidates && manageCandidates.classList.contains("active")) {
        // Cập nhật trạng thái của menu sidebar
        document.querySelector(".sidebar li.active").classList.remove("active");
        manageCandidatesTab.parentElement.classList.add("active");
    } else if (manageEmployers && manageEmployers.classList.contains("active")) {
        // Cập nhật trạng thái của menu sidebar
        document.querySelector(".sidebar li.active").classList.remove("active");
        manageEmployersTab.parentElement.classList.add("active");
    }
    
    
    // Cập nhật link cho phân trang
	const paginationContainer = document.getElementById("pagination-container");

    // Lấy giá trị từ data attributes
    const isSearch = paginationContainer.getAttribute("data-is-search") === "true";
    const mainContent = paginationContainer.getAttribute("data-main-content");
    const searchInput = document.getElementById("searchInput").value;

    // Gắn sự kiện cho các link phân trang
    const paginationLinks = document.querySelectorAll(".pagination-link");
    paginationLinks.forEach(link => {
        link.addEventListener("click", function (e) {
            e.preventDefault();
            const page = this.getAttribute("data-page");

            // Tạo URL động
            const baseUrl = isSearch 
                ? `/sem5_job_portal/admin/search`
                : `/sem5_job_portal/admin/list`;

            const params = new URLSearchParams({
                mainContent: mainContent,
                page: page
            });

            if (isSearch && searchInput) {
                params.append("searchText", searchInput.trim());
            }

            // Điều hướng đến URL mới
            window.location.href = `${baseUrl}?${params.toString()}`;
        });
    });

});