document.addEventListener("DOMContentLoaded", () => {
    const manageUsersTab = document.getElementById("manageUsersTab");
    const manageJobsTab = document.getElementById("manageJobsTab");
    const manageUsers = document.getElementById("manageUsers");
    const manageJobs = document.getElementById("manageJobs");
	
	if (manageUsers.classList.contains("active")) {
		// Cập nhật trạng thái của menu sidebar
        document.querySelector(".sidebar li.active").classList.remove("active");
        manageUsersTab.parentElement.classList.add("active");
	} else if (manageJobs.classList.contains("active")){
        // Cập nhật trạng thái của menu sidebar
        document.querySelector(".sidebar li.active").classList.remove("active");
        manageJobsTab.parentElement.classList.add("active");
	}
	
    // Tab switching
    manageUsersTab.addEventListener("click", () => {
        window.location.href = "admin/list?mainContent=manageUsers";
    });

    manageJobsTab.addEventListener("click", () => {
		window.location.href = "admin/list?mainContent=manageJobs";
    });
});