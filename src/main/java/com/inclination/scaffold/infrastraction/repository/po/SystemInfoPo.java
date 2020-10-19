package com.inclination.scaffold.infrastraction.repository.po;

import java.util.Date;
import javax.persistence.*;

@Table(name = "system_info")
public class SystemInfoPo {
    /**
     * 主键
     */
    @Id
    private String id;

    /**
     * 项目id，scaffold的项目id
     */
    @Column(name = "project_id")
    private Integer projectId;

    @Column(name = "jvm_memory_max")
    private String jvmMemoryMax;

    @Column(name = "jvm_threads_states")
    private String jvmThreadsStates;

    @Column(name = "jvm_gc_pause")
    private String jvmGcPause;

    @Column(name = "jvm_gc_memory_promoted")
    private String jvmGcMemoryPromoted;

    @Column(name = "jvm_memory_used")
    private String jvmMemoryUsed;

    @Column(name = "jvm_gc_max_data_size")
    private String jvmGcMaxDataSize;

    @Column(name = "jvm_memory_committed")
    private String jvmMemoryCommitted;

    @Column(name = "system_cpu_count")
    private String systemCpuCount;

    @Column(name = "logback_events")
    private String logbackEvents;

    @Column(name = "tomcat_global_sent")
    private String tomcatGlobalSent;

    @Column(name = "jvm_buffer_memory_used")
    private String jvmBufferMemoryUsed;

    @Column(name = "tomcat_sessions_created")
    private String tomcatSessionsCreated;

    @Column(name = "jvm_threads_daemon")
    private String jvmThreadsDaemon;

    @Column(name = "system_cpu_usage")
    private String systemCpuUsage;

    @Column(name = "jvm_gc_memory_allocated")
    private String jvmGcMemoryAllocated;

    @Column(name = "tomcat_global_request_max")
    private String tomcatGlobalRequestMax;

    @Column(name = "tomcat_global_request")
    private String tomcatGlobalRequest;

    @Column(name = "tomcat_sessions_expired")
    private String tomcatSessionsExpired;

    @Column(name = "jvm_threads_live")
    private String jvmThreadsLive;

    @Column(name = "jvm_threads_peak")
    private String jvmThreadsPeak;

    @Column(name = "tomcat_global_received")
    private String tomcatGlobalReceived;

    @Column(name = "process_uptime")
    private Date processUptime;

    @Column(name = "tomcat_sessions_rejected")
    private String tomcatSessionsRejected;

    @Column(name = "process_cpu_usage")
    private String processCpuUsage;

    @Column(name = "tomcat_threads_config_max")
    private String tomcatThreadsConfigMax;

    @Column(name = "jvm_classes_loaded")
    private String jvmClassesLoaded;

    @Column(name = "jvm_classes_unloaded")
    private String jvmClassesUnloaded;

    @Column(name = "tomcat_global_error")
    private String tomcatGlobalError;

    @Column(name = "tomcat_sessions_active_current")
    private String tomcatSessionsActiveCurrent;

    @Column(name = "http_server_requests")
    private String httpServerRequests;

    @Column(name = "tomcat_sessions_alive_max")
    private String tomcatSessionsAliveMax;

    @Column(name = "jvm_gc_live_data_size")
    private String jvmGcLiveDataSize;

    @Column(name = "tomcat_threads_current")
    private String tomcatThreadsCurrent;

    @Column(name = "jvm_buffer_count")
    private String jvmBufferCount;

    @Column(name = "jvm_buffer_total_capacity")
    private String jvmBufferTotalCapacity;

    @Column(name = "tomcat_sessions_active_max")
    private String tomcatSessionsActiveMax;

    @Column(name = "tomcat_threads_busy")
    private String tomcatThreadsBusy;

    @Column(name = "process_start_time")
    private Date processStartTime;

    /**
     * 创建的时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取项目id，scaffold的项目id
     *
     * @return project_id - 项目id，scaffold的项目id
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * 设置项目id，scaffold的项目id
     *
     * @param projectId 项目id，scaffold的项目id
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    /**
     * @return jvm_memory_max
     */
    public String getJvmMemoryMax() {
        return jvmMemoryMax;
    }

    /**
     * @param jvmMemoryMax
     */
    public void setJvmMemoryMax(String jvmMemoryMax) {
        this.jvmMemoryMax = jvmMemoryMax == null ? null : jvmMemoryMax.trim();
    }

    /**
     * @return jvm_threads_states
     */
    public String getJvmThreadsStates() {
        return jvmThreadsStates;
    }

    /**
     * @param jvmThreadsStates
     */
    public void setJvmThreadsStates(String jvmThreadsStates) {
        this.jvmThreadsStates = jvmThreadsStates == null ? null : jvmThreadsStates.trim();
    }

    /**
     * @return jvm_gc_pause
     */
    public String getJvmGcPause() {
        return jvmGcPause;
    }

    /**
     * @param jvmGcPause
     */
    public void setJvmGcPause(String jvmGcPause) {
        this.jvmGcPause = jvmGcPause == null ? null : jvmGcPause.trim();
    }

    /**
     * @return jvm_gc_memory_promoted
     */
    public String getJvmGcMemoryPromoted() {
        return jvmGcMemoryPromoted;
    }

    /**
     * @param jvmGcMemoryPromoted
     */
    public void setJvmGcMemoryPromoted(String jvmGcMemoryPromoted) {
        this.jvmGcMemoryPromoted = jvmGcMemoryPromoted == null ? null : jvmGcMemoryPromoted.trim();
    }

    /**
     * @return jvm_memory_used
     */
    public String getJvmMemoryUsed() {
        return jvmMemoryUsed;
    }

    /**
     * @param jvmMemoryUsed
     */
    public void setJvmMemoryUsed(String jvmMemoryUsed) {
        this.jvmMemoryUsed = jvmMemoryUsed == null ? null : jvmMemoryUsed.trim();
    }

    /**
     * @return jvm_gc_max_data_size
     */
    public String getJvmGcMaxDataSize() {
        return jvmGcMaxDataSize;
    }

    /**
     * @param jvmGcMaxDataSize
     */
    public void setJvmGcMaxDataSize(String jvmGcMaxDataSize) {
        this.jvmGcMaxDataSize = jvmGcMaxDataSize == null ? null : jvmGcMaxDataSize.trim();
    }

    /**
     * @return jvm_memory_committed
     */
    public String getJvmMemoryCommitted() {
        return jvmMemoryCommitted;
    }

    /**
     * @param jvmMemoryCommitted
     */
    public void setJvmMemoryCommitted(String jvmMemoryCommitted) {
        this.jvmMemoryCommitted = jvmMemoryCommitted == null ? null : jvmMemoryCommitted.trim();
    }

    /**
     * @return system_cpu_count
     */
    public String getSystemCpuCount() {
        return systemCpuCount;
    }

    /**
     * @param systemCpuCount
     */
    public void setSystemCpuCount(String systemCpuCount) {
        this.systemCpuCount = systemCpuCount == null ? null : systemCpuCount.trim();
    }

    /**
     * @return logback_events
     */
    public String getLogbackEvents() {
        return logbackEvents;
    }

    /**
     * @param logbackEvents
     */
    public void setLogbackEvents(String logbackEvents) {
        this.logbackEvents = logbackEvents == null ? null : logbackEvents.trim();
    }

    /**
     * @return tomcat_global_sent
     */
    public String getTomcatGlobalSent() {
        return tomcatGlobalSent;
    }

    /**
     * @param tomcatGlobalSent
     */
    public void setTomcatGlobalSent(String tomcatGlobalSent) {
        this.tomcatGlobalSent = tomcatGlobalSent == null ? null : tomcatGlobalSent.trim();
    }

    /**
     * @return jvm_buffer_memory_used
     */
    public String getJvmBufferMemoryUsed() {
        return jvmBufferMemoryUsed;
    }

    /**
     * @param jvmBufferMemoryUsed
     */
    public void setJvmBufferMemoryUsed(String jvmBufferMemoryUsed) {
        this.jvmBufferMemoryUsed = jvmBufferMemoryUsed == null ? null : jvmBufferMemoryUsed.trim();
    }

    /**
     * @return tomcat_sessions_created
     */
    public String getTomcatSessionsCreated() {
        return tomcatSessionsCreated;
    }

    /**
     * @param tomcatSessionsCreated
     */
    public void setTomcatSessionsCreated(String tomcatSessionsCreated) {
        this.tomcatSessionsCreated = tomcatSessionsCreated == null ? null : tomcatSessionsCreated.trim();
    }

    /**
     * @return jvm_threads_daemon
     */
    public String getJvmThreadsDaemon() {
        return jvmThreadsDaemon;
    }

    /**
     * @param jvmThreadsDaemon
     */
    public void setJvmThreadsDaemon(String jvmThreadsDaemon) {
        this.jvmThreadsDaemon = jvmThreadsDaemon == null ? null : jvmThreadsDaemon.trim();
    }

    /**
     * @return system_cpu_usage
     */
    public String getSystemCpuUsage() {
        return systemCpuUsage;
    }

    /**
     * @param systemCpuUsage
     */
    public void setSystemCpuUsage(String systemCpuUsage) {
        this.systemCpuUsage = systemCpuUsage == null ? null : systemCpuUsage.trim();
    }

    /**
     * @return jvm_gc_memory_allocated
     */
    public String getJvmGcMemoryAllocated() {
        return jvmGcMemoryAllocated;
    }

    /**
     * @param jvmGcMemoryAllocated
     */
    public void setJvmGcMemoryAllocated(String jvmGcMemoryAllocated) {
        this.jvmGcMemoryAllocated = jvmGcMemoryAllocated == null ? null : jvmGcMemoryAllocated.trim();
    }

    /**
     * @return tomcat_global_request_max
     */
    public String getTomcatGlobalRequestMax() {
        return tomcatGlobalRequestMax;
    }

    /**
     * @param tomcatGlobalRequestMax
     */
    public void setTomcatGlobalRequestMax(String tomcatGlobalRequestMax) {
        this.tomcatGlobalRequestMax = tomcatGlobalRequestMax == null ? null : tomcatGlobalRequestMax.trim();
    }

    /**
     * @return tomcat_global_request
     */
    public String getTomcatGlobalRequest() {
        return tomcatGlobalRequest;
    }

    /**
     * @param tomcatGlobalRequest
     */
    public void setTomcatGlobalRequest(String tomcatGlobalRequest) {
        this.tomcatGlobalRequest = tomcatGlobalRequest == null ? null : tomcatGlobalRequest.trim();
    }

    /**
     * @return tomcat_sessions_expired
     */
    public String getTomcatSessionsExpired() {
        return tomcatSessionsExpired;
    }

    /**
     * @param tomcatSessionsExpired
     */
    public void setTomcatSessionsExpired(String tomcatSessionsExpired) {
        this.tomcatSessionsExpired = tomcatSessionsExpired == null ? null : tomcatSessionsExpired.trim();
    }

    /**
     * @return jvm_threads_live
     */
    public String getJvmThreadsLive() {
        return jvmThreadsLive;
    }

    /**
     * @param jvmThreadsLive
     */
    public void setJvmThreadsLive(String jvmThreadsLive) {
        this.jvmThreadsLive = jvmThreadsLive == null ? null : jvmThreadsLive.trim();
    }

    /**
     * @return jvm_threads_peak
     */
    public String getJvmThreadsPeak() {
        return jvmThreadsPeak;
    }

    /**
     * @param jvmThreadsPeak
     */
    public void setJvmThreadsPeak(String jvmThreadsPeak) {
        this.jvmThreadsPeak = jvmThreadsPeak == null ? null : jvmThreadsPeak.trim();
    }

    /**
     * @return tomcat_global_received
     */
    public String getTomcatGlobalReceived() {
        return tomcatGlobalReceived;
    }

    /**
     * @param tomcatGlobalReceived
     */
    public void setTomcatGlobalReceived(String tomcatGlobalReceived) {
        this.tomcatGlobalReceived = tomcatGlobalReceived == null ? null : tomcatGlobalReceived.trim();
    }

    /**
     * @return process_uptime
     */
    public Date getProcessUptime() {
        return processUptime;
    }

    /**
     * @param processUptime
     */
    public void setProcessUptime(Date processUptime) {
        this.processUptime = processUptime;
    }

    /**
     * @return tomcat_sessions_rejected
     */
    public String getTomcatSessionsRejected() {
        return tomcatSessionsRejected;
    }

    /**
     * @param tomcatSessionsRejected
     */
    public void setTomcatSessionsRejected(String tomcatSessionsRejected) {
        this.tomcatSessionsRejected = tomcatSessionsRejected == null ? null : tomcatSessionsRejected.trim();
    }

    /**
     * @return process_cpu_usage
     */
    public String getProcessCpuUsage() {
        return processCpuUsage;
    }

    /**
     * @param processCpuUsage
     */
    public void setProcessCpuUsage(String processCpuUsage) {
        this.processCpuUsage = processCpuUsage == null ? null : processCpuUsage.trim();
    }

    /**
     * @return tomcat_threads_config_max
     */
    public String getTomcatThreadsConfigMax() {
        return tomcatThreadsConfigMax;
    }

    /**
     * @param tomcatThreadsConfigMax
     */
    public void setTomcatThreadsConfigMax(String tomcatThreadsConfigMax) {
        this.tomcatThreadsConfigMax = tomcatThreadsConfigMax == null ? null : tomcatThreadsConfigMax.trim();
    }

    /**
     * @return jvm_classes_loaded
     */
    public String getJvmClassesLoaded() {
        return jvmClassesLoaded;
    }

    /**
     * @param jvmClassesLoaded
     */
    public void setJvmClassesLoaded(String jvmClassesLoaded) {
        this.jvmClassesLoaded = jvmClassesLoaded == null ? null : jvmClassesLoaded.trim();
    }

    /**
     * @return jvm_classes_unloaded
     */
    public String getJvmClassesUnloaded() {
        return jvmClassesUnloaded;
    }

    /**
     * @param jvmClassesUnloaded
     */
    public void setJvmClassesUnloaded(String jvmClassesUnloaded) {
        this.jvmClassesUnloaded = jvmClassesUnloaded == null ? null : jvmClassesUnloaded.trim();
    }

    /**
     * @return tomcat_global_error
     */
    public String getTomcatGlobalError() {
        return tomcatGlobalError;
    }

    /**
     * @param tomcatGlobalError
     */
    public void setTomcatGlobalError(String tomcatGlobalError) {
        this.tomcatGlobalError = tomcatGlobalError == null ? null : tomcatGlobalError.trim();
    }

    /**
     * @return tomcat_sessions_active_current
     */
    public String getTomcatSessionsActiveCurrent() {
        return tomcatSessionsActiveCurrent;
    }

    /**
     * @param tomcatSessionsActiveCurrent
     */
    public void setTomcatSessionsActiveCurrent(String tomcatSessionsActiveCurrent) {
        this.tomcatSessionsActiveCurrent = tomcatSessionsActiveCurrent == null ? null : tomcatSessionsActiveCurrent.trim();
    }

    /**
     * @return http_server_requests
     */
    public String getHttpServerRequests() {
        return httpServerRequests;
    }

    /**
     * @param httpServerRequests
     */
    public void setHttpServerRequests(String httpServerRequests) {
        this.httpServerRequests = httpServerRequests == null ? null : httpServerRequests.trim();
    }

    /**
     * @return tomcat_sessions_alive_max
     */
    public String getTomcatSessionsAliveMax() {
        return tomcatSessionsAliveMax;
    }

    /**
     * @param tomcatSessionsAliveMax
     */
    public void setTomcatSessionsAliveMax(String tomcatSessionsAliveMax) {
        this.tomcatSessionsAliveMax = tomcatSessionsAliveMax == null ? null : tomcatSessionsAliveMax.trim();
    }

    /**
     * @return jvm_gc_live_data_size
     */
    public String getJvmGcLiveDataSize() {
        return jvmGcLiveDataSize;
    }

    /**
     * @param jvmGcLiveDataSize
     */
    public void setJvmGcLiveDataSize(String jvmGcLiveDataSize) {
        this.jvmGcLiveDataSize = jvmGcLiveDataSize == null ? null : jvmGcLiveDataSize.trim();
    }

    /**
     * @return tomcat_threads_current
     */
    public String getTomcatThreadsCurrent() {
        return tomcatThreadsCurrent;
    }

    /**
     * @param tomcatThreadsCurrent
     */
    public void setTomcatThreadsCurrent(String tomcatThreadsCurrent) {
        this.tomcatThreadsCurrent = tomcatThreadsCurrent == null ? null : tomcatThreadsCurrent.trim();
    }

    /**
     * @return jvm_buffer_count
     */
    public String getJvmBufferCount() {
        return jvmBufferCount;
    }

    /**
     * @param jvmBufferCount
     */
    public void setJvmBufferCount(String jvmBufferCount) {
        this.jvmBufferCount = jvmBufferCount == null ? null : jvmBufferCount.trim();
    }

    /**
     * @return jvm_buffer_total_capacity
     */
    public String getJvmBufferTotalCapacity() {
        return jvmBufferTotalCapacity;
    }

    /**
     * @param jvmBufferTotalCapacity
     */
    public void setJvmBufferTotalCapacity(String jvmBufferTotalCapacity) {
        this.jvmBufferTotalCapacity = jvmBufferTotalCapacity == null ? null : jvmBufferTotalCapacity.trim();
    }

    /**
     * @return tomcat_sessions_active_max
     */
    public String getTomcatSessionsActiveMax() {
        return tomcatSessionsActiveMax;
    }

    /**
     * @param tomcatSessionsActiveMax
     */
    public void setTomcatSessionsActiveMax(String tomcatSessionsActiveMax) {
        this.tomcatSessionsActiveMax = tomcatSessionsActiveMax == null ? null : tomcatSessionsActiveMax.trim();
    }

    /**
     * @return tomcat_threads_busy
     */
    public String getTomcatThreadsBusy() {
        return tomcatThreadsBusy;
    }

    /**
     * @param tomcatThreadsBusy
     */
    public void setTomcatThreadsBusy(String tomcatThreadsBusy) {
        this.tomcatThreadsBusy = tomcatThreadsBusy == null ? null : tomcatThreadsBusy.trim();
    }

    /**
     * @return process_start_time
     */
    public Date getProcessStartTime() {
        return processStartTime;
    }

    /**
     * @param processStartTime
     */
    public void setProcessStartTime(Date processStartTime) {
        this.processStartTime = processStartTime;
    }

    /**
     * 获取创建的时间
     *
     * @return create_time - 创建的时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建的时间
     *
     * @param createTime 创建的时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}